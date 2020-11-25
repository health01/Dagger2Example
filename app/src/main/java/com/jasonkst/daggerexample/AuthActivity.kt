package com.jasonkst.daggerexample

import android.os.Bundle
import dagger.android.DaggerActivity
import javax.inject.Inject

class AuthActivity : DaggerActivity() {
    private val TAG = "AuthActivity"

    @Inject
    lateinit var defaultString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(TAG + defaultString)
    }

}