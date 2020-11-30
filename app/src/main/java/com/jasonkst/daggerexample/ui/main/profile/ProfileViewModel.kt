package com.jasonkst.daggerexample.ui.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {
    private val TAG = "DaggerExample"

    init {
        Log.d(TAG, "ProfileViewModel: viewmodel is ready...")
    }
}