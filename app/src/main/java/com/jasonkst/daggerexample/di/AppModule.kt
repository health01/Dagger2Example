package com.jasonkst.daggerexample.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jasonkst.daggerexample.R
import dagger.Module
import dagger.Provides

@Module
object AppModule {


    @Provides
    fun getApp(application: Application?): Boolean {
        return application == null
    }

    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    fun provideAppDrawable(application: Application): Drawable {
        return application.let { ContextCompat.getDrawable(it, R.drawable.logo)!! }
    }
}