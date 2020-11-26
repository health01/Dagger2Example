package com.jasonkst.daggerexample.network.auth

import com.jasonkst.daggerexample.models.User
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User>
}