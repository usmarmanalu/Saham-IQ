package com.dicoding.sahamiq.di

import androidx.lifecycle.*
import com.dicoding.sahamiq.core.ui.*
import com.dicoding.sahamiq.detail.*
import com.dicoding.sahamiq.favorite.*
import com.dicoding.sahamiq.home.*
import dagger.*
import dagger.multibindings.*


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}