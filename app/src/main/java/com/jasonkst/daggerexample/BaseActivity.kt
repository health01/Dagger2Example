package com.jasonkst.daggerexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.jasonkst.daggerexample.ui.auth.AuthActivity
import com.jasonkst.daggerexample.ui.auth.AuthResource
import com.microsoft.appcenter.crashes.Crashes

import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    private val TAG = "DaggerExample"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, {
            when (it) {
                is AuthResource.Loading -> Log.d(
                    TAG,
                    "onChanged: BaseActivity: LOADING..."
                )


                is AuthResource.Authenticated ->
                    Log.d(
                        TAG,
                        "onChanged: BaseActivity: AUTHENTICATED... " + "Authenticated as: " + it.data?.email.toString()
                    )


                is AuthResource.Error -> Log.d(TAG, "onChanged: BaseActivity: ERROR...")

                is AuthResource.Logout -> {
                    Log.d(
                        TAG,
                        "onChanged: BaseActivity: NOT AUTHENTICATED. Navigating to Login screen."
                    )
                    navLoginScreen()
                }
            }
        })
    }

    private fun navLoginScreen() {
        Intent(this, AuthActivity::class.java).let {
            startActivity(it)
            finish()
        }
    }
}