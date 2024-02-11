package com.dicoding.sahamiq.core.di

import androidx.room.*
import com.dicoding.sahamiq.core.data.source.*
import com.dicoding.sahamiq.core.data.source.local.*
import com.dicoding.sahamiq.core.data.source.local.room.*
import com.dicoding.sahamiq.core.data.source.remote.*
import com.dicoding.sahamiq.core.data.source.remote.network.*
import com.dicoding.sahamiq.core.domain.repository.*
import com.dicoding.sahamiq.core.utils.*
import okhttp3.*
import okhttp3.logging.*
import org.koin.android.ext.koin.*
import org.koin.dsl.*
import retrofit2.*
import retrofit2.converter.gson.*
import java.util.concurrent.*

val databaseModule = module {
    factory { get<SahamTrendingDatabase>().sahamTrendingDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            SahamTrendingDatabase::class.java, "Saham.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.goapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }

    single<ISahamRepository> {
        SahamTrendingRepository(
            get(),
            get(),
            get(),
        )
    }
}