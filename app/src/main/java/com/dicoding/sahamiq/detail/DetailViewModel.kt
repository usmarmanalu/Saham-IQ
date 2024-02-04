package com.dicoding.sahamiq.detail

import androidx.lifecycle.*
import com.dicoding.sahamiq.core.domain.model.*
import com.dicoding.sahamiq.core.domain.usecase.*
import javax.inject.*

class DetailViewModel @Inject constructor(private val sahamUseCase: SahamUseCase) : ViewModel() {

    fun setFavoriteSaham(saham: Saham, newStatus: Boolean) =
        sahamUseCase.setFavoriteSaham(saham, newStatus)
}