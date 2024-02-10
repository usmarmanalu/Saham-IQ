package com.dicoding.sahamiq.favorite

import androidx.lifecycle.*
import com.dicoding.sahamiq.core.domain.usecase.*

class FavoriteViewModel constructor(sahamUseCase: SahamUseCase) : ViewModel() {

    val favoriteSaham = sahamUseCase.getFavoriteSaham().asLiveData()
}