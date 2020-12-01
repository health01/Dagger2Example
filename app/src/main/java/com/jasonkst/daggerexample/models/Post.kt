package com.jasonkst.daggerexample.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    @Expose
    var userId: Int? = 0,

    @SerializedName("id")
    @Expose
    var id: Int? = 0,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("body")
    @Expose
    var body: String? = null
)