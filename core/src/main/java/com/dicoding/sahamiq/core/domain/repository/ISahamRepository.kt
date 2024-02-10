package com.dicoding.sahamiq.core.domain.repository

import com.dicoding.sahamiq.core.data.source.*
import com.dicoding.sahamiq.core.domain.model.*
import kotlinx.coroutines.flow.*

interface ISahamRepository {

    fun getAllSahamTrending(): Flow<Resource<List<Saham>>>

    fun getFavoriteSahamTrending(): Flow<List<Saham>>

    fun setFavoriteSahamTrending(saham: Saham, state: Boolean)

}