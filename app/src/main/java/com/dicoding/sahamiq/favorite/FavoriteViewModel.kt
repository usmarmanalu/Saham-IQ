package com.dicoding.sahamiq.favorite

import androidx.lifecycle.*
import com.dicoding.sahamiq.core.domain.usecase.*
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(sahamUseCase: SahamUseCase) : ViewModel() {

    val favoriteSaham = sahamUseCase.getFavoriteSaham().asLiveData()
}