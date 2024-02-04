package com.dicoding.sahamiq.di

import com.dicoding.sahamiq.core.domain.usecase.*
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideSahamUseCase(sahamInteractor: SahamInteractor): SahamUseCase
}