package com.jasonkst.daggerexample.network.main

import com.jasonkst.daggerexample.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("users/{id}")
    fun getUser(@Path("userId") id: Int): Flowable<List<Post>>
}