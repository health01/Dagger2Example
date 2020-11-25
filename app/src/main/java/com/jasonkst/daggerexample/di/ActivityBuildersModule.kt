package com.jasonkst.daggerexample.di

import com.jasonkst.daggerexample.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity
}