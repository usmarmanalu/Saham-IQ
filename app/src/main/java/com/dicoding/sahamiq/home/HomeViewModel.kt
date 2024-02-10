package com.dicoding.sahamiq.home

import androidx.lifecycle.*
import com.dicoding.sahamiq.core.domain.usecase.*

class HomeViewModel(sahamUseCase: SahamUseCase) : ViewModel() {

    val saham = sahamUseCase.getAllSaham().asLiveData()

}