package com.jasonkst.daggerexample.di

import android.app.Application
import com.jasonkst.daggerexample.BaseApplication
import com.jasonkst.daggerexample.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {
//    fun authSubcomponent(): ActivityBuildersModule.AuthActivitySubcomponent.Factory

    fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent?
    }
}