package com.jasonkst.daggerexample.di.auth

import com.jasonkst.daggerexample.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AuthModule {
    @AuthScope
    @Provides
    fun provideSessionApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}