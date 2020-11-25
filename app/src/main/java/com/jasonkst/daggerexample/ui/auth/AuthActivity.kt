package com.jasonkst.daggerexample.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.jasonkst.daggerexample.R
import dagger.android.DaggerActivity
import javax.inject.Inject

class AuthActivity : DaggerActivity() {

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_auth)
        super.onCreate(savedInstanceState)
        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }
}