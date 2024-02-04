package com.dicoding.sahamiq.home

import androidx.lifecycle.*
import com.dicoding.sahamiq.core.domain.usecase.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(sahamUseCase: SahamUseCase): ViewModel() {

    val saham = sahamUseCase.getAllSaham().asLiveData()

}