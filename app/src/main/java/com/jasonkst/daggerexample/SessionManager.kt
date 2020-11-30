package com.jasonkst.daggerexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.jasonkst.daggerexample.models.User
import com.jasonkst.daggerexample.ui.auth.AuthResource
import javax.inject.Inject


class SessionManager @Inject constructor() {
    private val TAG = "DaggerExample"

    // data
    private val cachedUser: MediatorLiveData<AuthResource<User>> =
        MediatorLiveData<AuthResource<User>>()

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        cachedUser.addSource(source) { it ->
            cachedUser.apply {
                value = it
                removeSource(source)
            }
        }
    }

    fun logout() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthResource.Logout()
    }

    fun getAuthUser() = cachedUser
}