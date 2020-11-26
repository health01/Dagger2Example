package com.jasonkst.daggerexample.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.jasonkst.daggerexample.R
import com.jasonkst.daggerexample.viewModels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {
    private val TAG = "AuthActivity"
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
        authViewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()
        subscribeObservers()
    }

    fun subscribeObservers() {
        authViewModel.authUser.observe(this, Observer { user ->
            Log.d(TAG, "onChanged: $user")
        })
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }

    private fun loginButtonClick() {
        if (TextUtils.isEmpty(user_id_input.text.toString())) {
            return
        }
        authViewModel.authenticateWithId(Integer.parseInt(user_id_input.text.toString()))
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.login_button -> loginButtonClick()
            else -> Log.d(TAG, "else")
        }
    }
}