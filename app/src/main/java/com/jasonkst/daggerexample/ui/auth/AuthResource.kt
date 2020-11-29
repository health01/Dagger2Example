package com.jasonkst.daggerexample.ui.auth

// A generic class that contains data and status about loading this data.
sealed class AuthResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Authenticated<T>(data: T) : AuthResource<T>(data)
    class Error<T>(data: T? = null, message: String) : AuthResource<T>(data, message)
    class Loading<T>(data: T? = null) : AuthResource<T>(data)
    class Logout<T> : AuthResource<T>()
}