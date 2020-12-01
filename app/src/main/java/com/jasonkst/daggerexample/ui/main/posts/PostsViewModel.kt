package com.jasonkst.daggerexample.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jasonkst.daggerexample.SessionManager
import com.jasonkst.daggerexample.models.User
import com.jasonkst.daggerexample.ui.auth.AuthResource
import javax.inject.Inject

class PostsViewModel @Inject constructor(val sessionManager: SessionManager) : ViewModel() {
    private val TAG = "ProfileViewModel"

    init {
        Log.d(TAG, "ProfileViewModel: viewmodel is ready...")

    }

    fun getAuthenticatedUser(): LiveData<AuthResource<User>> = sessionManager.getAuthUser()
}