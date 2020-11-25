package com.jasonkst.daggerexample.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.jasonkst.daggerexample.R
import com.jasonkst.daggerexample.viewModels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authViewModel = ViewModelProvider(this,providerFactory).get(AuthViewModel::class.java)
        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }
}