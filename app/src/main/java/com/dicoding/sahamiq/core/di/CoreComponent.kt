package com.dicoding.sahamiq.core.di

import android.content.*
import com.dicoding.sahamiq.core.domain.repository.*
import dagger.*
import javax.inject.*

@Singleton
@Component(modules = [RepositoryModule::class])

interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): ISahamRepository
}