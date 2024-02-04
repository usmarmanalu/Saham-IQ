package com.dicoding.sahamiq.core.data.source.local

import com.dicoding.sahamiq.core.data.source.local.entity.SahamTrendingEntity
import com.dicoding.sahamiq.core.data.source.local.room.*
import kotlinx.coroutines.flow.*
import javax.inject.*

class LocalDataSource @Inject constructor(private val sahamTrendingDao: SahamTrendingDao) {

    fun getAllSaham(): Flow<List<SahamTrendingEntity>> = sahamTrendingDao.getAllSahamTrending()

    fun getFavoriteSaham(): Flow<List<SahamTrendingEntity>> = sahamTrendingDao.getFavoriteSaham()

    suspend fun insertSaham(sahamList: List<SahamTrendingEntity>) =
        sahamTrendingDao.insertSaham(sahamList)

    fun setFavoriteSaham(saham: SahamTrendingEntity, newState: Boolean) {
        saham.isFavorite = newState
        sahamTrendingDao.updateFavoriteSaham(saham)
    }
}