package com.dicoding.sahamiq.core.data.source.local.room

import androidx.room.*
import com.dicoding.sahamiq.core.data.source.local.entity.*
import kotlinx.coroutines.flow.*

@Dao
interface SahamTrendingDao {

    @Query("SELECT * FROM saham_trending")
    fun getAllSahamTrending(): Flow<List<SahamTrendingEntity>>

    @Query("SELECT * FROM saham_trending where isFavorite = 1")
    fun getFavoriteSaham(): Flow<List<SahamTrendingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaham(sahamTrending: List<SahamTrendingEntity>)

    @Update
    fun updateFavoriteSaham(sahamTrendingEntity: SahamTrendingEntity)

}
