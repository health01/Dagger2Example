package com.jasonkst.daggerexample.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.jasonkst.daggerexample.R
import com.jasonkst.daggerexample.models.User
import com.jasonkst.daggerexample.ui.main.MainActivity
import com.jasonkst.daggerexample.viewModels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {
    private val TAG = "AuthActivity"
    lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    @Named("app_user")
    lateinit var userNumber1: User

    @Inject
    @Named("auth_user")
    lateinit var userNumber2: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authViewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()
        subscribeObservers()


        Log.d(TAG, "onCreate: ${ Integer.toHexString(System.identityHashCode(userNumber1))}")
        Log.d(TAG, "onCreate: ${ Integer.toHexString(System.identityHashCode(userNumber2))}")
    }

    private fun subscribeObservers() {
        authViewModel.observeAuthState().observe(this,
            { userAuthResource ->
                userAuthResource?.apply {
                    when (this) {
                        is AuthResource.Loading -> showProgressBar(true)

                        is AuthResource.Authenticated -> {
                            showProgressBar(false)
                            Log.d(
                                TAG,
                                "onChanged: LOGIN SUCCESS: " + userAuthResource.data?.toString()
                            )
                            onLoginSuccess()
                        }

                        is AuthResource.Error -> {
                            Log.e(TAG, "onChanged: " + userAuthResource.message)
                            showProgressBar(false)
                            Toast.makeText(
                                this@AuthActivity,
                                """${userAuthResource.message.toString()}  Did you enter a number between 0 and 10?   """.trimIndent(),
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                        else -> showProgressBar(false)
                    }
                }
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

    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun onLoginSuccess() {
        Log.d(TAG, "onLoginSuccess: login successful!")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.login_button -> loginButtonClick()
            else -> Log.d(TAG, "else")
        }
    }
}