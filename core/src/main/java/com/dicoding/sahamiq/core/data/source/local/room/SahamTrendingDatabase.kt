package com.dicoding.sahamiq.core.data.source.local.room

import androidx.room.*
import com.dicoding.sahamiq.core.data.source.local.entity.*

@Database(entities = [SahamTrendingEntity::class, CompanyEntity::class], version = 1, exportSchema = false)
@TypeConverters(CompanyConverter::class)
abstract class SahamTrendingDatabase : RoomDatabase() {

    abstract fun sahamTrendingDao(): SahamTrendingDao
}