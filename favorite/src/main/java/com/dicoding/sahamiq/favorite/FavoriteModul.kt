package com.dicoding.sahamiq.favorite

import org.koin.androidx.viewmodel.dsl.*
import org.koin.dsl.*

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}