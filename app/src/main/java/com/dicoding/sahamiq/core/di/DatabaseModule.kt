package com.dicoding.sahamiq.core.di

import android.content.*
import androidx.room.*
import com.dicoding.sahamiq.core.data.source.local.room.*
import dagger.*
import javax.inject.*

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): SahamTrendingDatabase = Room.databaseBuilder(
        context,
        SahamTrendingDatabase::class.java, "com.dicoding.sahamiq.core.domain.model.Saham.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideSahamDao(database: SahamTrendingDatabase): SahamTrendingDao =
        database.sahamTrendingDao()
}