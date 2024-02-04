package com.dicoding.sahamiq.core.data.source.local.room

import com.dicoding.sahamiq.core.data.source.local.entity.CompanyEntity
import com.dicoding.sahamiq.core.data.source.local.entity.SahamTrendingEntity
import androidx.room.*
import com.dicoding.sahamiq.core.data.source.local.entity.*

@Database(entities = [SahamTrendingEntity::class, CompanyEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class SahamTrendingDatabase : RoomDatabase() {

    abstract fun sahamTrendingDao(): SahamTrendingDao
}