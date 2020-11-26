package com.jasonkst.daggerexample.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jasonkst.daggerexample.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(api: AuthApi) : ViewModel() {
    companion object {
        private const val TAG = "AuthViewModel"
    }
    init {
        Log.d(TAG, "AuthViewModel: AuthApi is working...")
    }
}

