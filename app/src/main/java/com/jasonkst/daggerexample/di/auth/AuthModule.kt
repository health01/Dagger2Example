package com.jasonkst.daggerexample.di.auth

import com.jasonkst.daggerexample.models.User
import com.jasonkst.daggerexample.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
object AuthModule {

    @AuthScope
    @Provides
    @Named("auth_user")
    fun someUser(): User {
        return User()
    }

    @AuthScope
    @Provides
    fun provideSessionApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}