package com.jasonkst.daggerexample.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
object AppModule {
    @Provides
    fun someString(): String {
        return "this is a test string"
    }

    @Provides
    fun getApp(application: Application?): Boolean {
        return application == null
    }
}