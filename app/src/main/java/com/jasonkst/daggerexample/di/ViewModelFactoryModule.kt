package com.jasonkst.daggerexample.di

import androidx.lifecycle.ViewModelProvider
import com.jasonkst.daggerexample.viewModels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

/*    Same way as above
    companion object{
        fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory {
            return viewModelFactory
        }
    }*/
}