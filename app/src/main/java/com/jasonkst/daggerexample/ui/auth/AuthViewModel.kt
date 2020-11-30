package com.jasonkst.daggerexample.ui.auth


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.jasonkst.daggerexample.SessionManager
import com.jasonkst.daggerexample.models.User
import com.jasonkst.daggerexample.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {
    companion object {
        private val TAG = "AuthViewModel"
    }

    fun authenticateWithId(userId: Int) {
        Log.d(TAG, "attemptLogin: attempting to login.")
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .onErrorReturn {
                    User().apply { id = -1 }
                }.subscribeOn(Schedulers.io())
                // wrap User object in AuthResource
                .map { user ->
                    if (user.id == -1) {
                        AuthResource.Error(message = "Could not authenticate")
                    } else AuthResource.Authenticated(user)
                }.subscribeOn(Schedulers.io())
        )
    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }
}

