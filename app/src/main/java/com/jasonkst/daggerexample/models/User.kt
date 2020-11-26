package com.jasonkst.daggerexample.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") @Expose
    var id: Int? = 0,

    @SerializedName("username") @Expose
    var username: String? = null,

    @SerializedName("email") @Expose
    var email: String? = null,

    @SerializedName("website") @Expose
    var website: String? = null
)