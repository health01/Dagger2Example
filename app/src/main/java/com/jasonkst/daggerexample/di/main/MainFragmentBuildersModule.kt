package com.jasonkst.daggerexample.di.main

import com.jasonkst.daggerexample.ui.main.posts.PostsFragment
import com.jasonkst.daggerexample.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): PostsFragment
}