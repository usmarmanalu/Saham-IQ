package com.dicoding.sahamiq.di

import com.dicoding.sahamiq.core.di.*
import com.dicoding.sahamiq.detail.*
import com.dicoding.sahamiq.favorite.*
import com.dicoding.sahamiq.home.*
import dagger.Component


@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailActivity)
}