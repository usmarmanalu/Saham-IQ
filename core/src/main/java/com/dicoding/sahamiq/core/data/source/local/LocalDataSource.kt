package com.dicoding.sahamiq.core.data.source.local

import com.dicoding.sahamiq.core.data.source.local.entity.*
import com.dicoding.sahamiq.core.data.source.local.room.*
import kotlinx.coroutines.flow.*

class LocalDataSource constructor(private val sahamTrendingDao: SahamTrendingDao) {

    fun getAllSaham(): Flow<List<SahamTrendingEntity>> = sahamTrendingDao.getAllSahamTrending()

    fun getFavoriteSaham(): Flow<List<SahamTrendingEntity>> = sahamTrendingDao.getFavoriteSaham()

    suspend fun insertSaham(sahamList: List<SahamTrendingEntity>) =
        sahamTrendingDao.insertSaham(sahamList)

    fun setFavoriteSaham(saham: SahamTrendingEntity, newState: Boolean) {
        saham.isFavorite = newState
        sahamTrendingDao.updateFavoriteSaham(saham)
    }
}