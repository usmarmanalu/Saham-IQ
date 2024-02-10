package com.dicoding.sahamiq.di

import com.dicoding.sahamiq.core.domain.usecase.*
import com.dicoding.sahamiq.detail.*
import com.dicoding.sahamiq.home.*
import org.koin.androidx.viewmodel.dsl.*
import org.koin.dsl.*


val useCaseModule = module {
    factory<SahamUseCase> { SahamInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}