package com.jasonkst.daggerexample.network.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AuthApi {
    @get:GET("fake")
    val getFakeStuff: Call<ResponseBody?>?
}