package com.dicoding.sahamiq.core.domain.usecase

import com.dicoding.sahamiq.core.data.source.*
import com.dicoding.sahamiq.core.domain.model.*
import kotlinx.coroutines.flow.*

interface SahamUseCase {
    fun getAllSaham(): Flow<Resource<List<Saham>>>

    fun getFavoriteSaham(): Flow<List<Saham>>

    fun setFavoriteSaham(saham: Saham, state: Boolean)
}