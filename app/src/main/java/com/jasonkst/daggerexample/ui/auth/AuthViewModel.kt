package com.jasonkst.daggerexample.ui.auth


import androidx.lifecycle.*
import com.jasonkst.daggerexample.models.User
import com.jasonkst.daggerexample.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {
    companion object {
        private val TAG = "AuthViewModel"
    }

    var authUser = MediatorLiveData<User>()
    private val authApi: AuthApi = authApi

    fun authenticateWithId(userId: Int) {
        val source: LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId).subscribeOn(Schedulers.io())
        )
        authUser.addSource(source,
            Observer<User?> { user ->
                authUser.value = user
                authUser.removeSource(source)
            })
    }
}

