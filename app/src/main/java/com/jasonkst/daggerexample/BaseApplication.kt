package com.jasonkst.daggerexample

import com.jasonkst.daggerexample.di.AppComponent
import com.jasonkst.daggerexample.di.DaggerAppComponent
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AppComponent? {
        return DaggerAppComponent.builder().application(this)?.build()
    }

    override fun onCreate() {
        super.onCreate()
        AppCenter.start(
            this, "8f65b5a6-2fc3-43c5-b68f-f109475479d6",
            Analytics::class.java, Crashes::class.java
        )
    }
}