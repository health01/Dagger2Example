package com.jasonkst.daggerexample.di.main

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import com.jasonkst.daggerexample.network.main.MainApi
import com.jasonkst.daggerexample.ui.main.posts.PostRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {
    @MainScope
    @Provides
    fun provideSessionApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

    @MainScope
    @Provides
    fun provideAdapter(): PostRecyclerAdapter = PostRecyclerAdapter()

    @MainScope
    @Provides
    fun provideLinearLayoutManager(application: Application,): LinearLayoutManager = LinearLayoutManager(application)
}