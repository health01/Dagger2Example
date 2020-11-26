package com.jasonkst.daggerexample.ui.auth

import android.util.Log
import io.reactivex.Observer

import androidx.lifecycle.ViewModel
import com.jasonkst.daggerexample.models.User
import com.jasonkst.daggerexample.network.auth.AuthApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {
    private fun getUserObserver(): Observer<User?> {
        return object : Observer<User?> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe...")
            }

            override fun onNext(user: User) {
                Log.d(TAG, "onNext:.$user")
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        }
    }

    companion object {
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG, "AuthViewModel: AuthApi is working...")
        authApi.getUser(1)?.apply {
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getUserObserver())
        }
    }
}

