package com.jasonkst.daggerexample.di

import com.jasonkst.daggerexample.di.auth.AuthViewModelsModule
import com.jasonkst.daggerexample.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class])
    abstract fun contributeAuthActivity(): AuthActivity
}