package com.jasonkst.daggerexample.di

import com.jasonkst.daggerexample.di.auth.AuthViewModelsModule
import com.jasonkst.daggerexample.ui.auth.AuthActivity
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuildersModule {
//    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class])
//    abstract fun contributeAuthActivity(): AuthActivity

    @Binds
    @IntoMap
    @ClassKey(AuthActivity::class)
    abstract fun bindAndroidInjectorFactory(
        builder: AuthActivitySubcomponent.Factory
    ): AndroidInjector.Factory<*>?

    @Subcomponent(modules = [AuthViewModelsModule::class])
    interface AuthActivitySubcomponent : AndroidInjector<AuthActivity?> {
        @Subcomponent.Factory
        interface Factory : AndroidInjector.Factory<AuthActivity?>
    }
}