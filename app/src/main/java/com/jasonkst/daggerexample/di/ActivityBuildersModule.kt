package com.jasonkst.daggerexample.di

import com.jasonkst.daggerexample.di.auth.AuthModule
import com.jasonkst.daggerexample.di.auth.AuthViewModelsModule
import com.jasonkst.daggerexample.ui.auth.AuthActivity
import com.jasonkst.daggerexample.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
    /*  @Binds
      @IntoMap
      @ClassKey(AuthActivity::class)
      abstract fun bindAndroidInjectorFactory(
          builder: AuthActivitySubcomponent.Factory
      ): AndroidInjector.Factory<*>?

      @Subcomponent(modules = [AuthViewModelsModule::class])
      interface AuthActivitySubcomponent : AndroidInjector<AuthActivity?> {
          @Subcomponent.Factory
          interface Factory : AndroidInjector.Factory<AuthActivity?>
      }*/
}