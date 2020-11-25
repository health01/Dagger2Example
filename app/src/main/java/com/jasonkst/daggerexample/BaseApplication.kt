package com.jasonkst.daggerexample

import com.jasonkst.daggerexample.di.AppComponent
import com.jasonkst.daggerexample.di.DaggerAppComponent
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AppComponent? {
        return DaggerAppComponent.builder().application(this)?.build()
    }
}