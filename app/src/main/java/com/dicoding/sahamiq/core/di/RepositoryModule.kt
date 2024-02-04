package com.dicoding.sahamiq.core.di

import com.dicoding.sahamiq.core.data.source.*
import com.dicoding.sahamiq.core.domain.repository.*
import dagger.*

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(sahamTrendingRepository: SahamTrendingRepository): ISahamRepository
}