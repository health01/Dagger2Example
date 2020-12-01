package com.jasonkst.daggerexample.di.main

import com.jasonkst.daggerexample.network.auth.AuthApi
import com.jasonkst.daggerexample.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {
    @Provides
    fun provideSessionApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)
}