package com.jasonkst.daggerexample.ui.auth


import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.jasonkst.daggerexample.models.User
import com.jasonkst.daggerexample.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {
    companion object {
        private val TAG = "AuthViewModel"
    }

    private var authUser = MediatorLiveData<AuthResource<User>>()

    fun authenticateWithId(userId: Int) {
        authUser.value = AuthResource.Loading()

// instead of calling onError, do this
        val source: LiveData<AuthResource<User>> =
            LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                    .onErrorReturn {
                        User().apply { id = -1 }
                    }.subscribeOn(Schedulers.io())
                    // wrap User object in AuthResource
                    .map { user ->
                        if (user.id == -1) {
                            AuthResource.Error(message = "Could not authenticate")
                        } else AuthResource.Authenticated(user)
                    })


        authUser.addSource(source) { userAuthResource ->
            authUser.apply {
                value = userAuthResource
                removeSource(source)
            }
        }
    }

    fun observeUser(): LiveData<AuthResource<User>> {
        return authUser
    }
}

