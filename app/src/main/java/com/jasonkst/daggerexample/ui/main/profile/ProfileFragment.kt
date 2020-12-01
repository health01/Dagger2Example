package com.jasonkst.daggerexample.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jasonkst.daggerexample.R
import com.jasonkst.daggerexample.models.User
import com.jasonkst.daggerexample.ui.auth.AuthResource
import com.jasonkst.daggerexample.viewModels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    private val TAG = "ProfileFragment"

    lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "Profile Fragment", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ProfileFragment. $this")
        viewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().apply {
            removeObservers(viewLifecycleOwner)
            observe(viewLifecycleOwner, { userAuthResource ->
                when (userAuthResource) {
                    is AuthResource.Authenticated -> {
                        userAuthResource.data?.let {
                            Log.d(
                                TAG, "onChanged: ProfileFragment: AUTHENTICATED... " +
                                        "Authenticated as: " + it.email
                            )
                            setUserDetails(it)
                        }

                    }
                    else -> {
                        Log.d(TAG, "onChanged: ProfileFragment: ERROR...")
                        userAuthResource.message?.let { setErrorDetails(it) }
                    }
                }
            })
        }
    }

    private fun setErrorDetails(message: String) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }

    private fun setUserDetails(user: User) {
        email.text = user.email
        username.text = user.username
        website.text = user.website
    }
}