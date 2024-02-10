package com.dicoding.sahamiq.core.data.source

import com.dicoding.sahamiq.core.data.source.local.*
import com.dicoding.sahamiq.core.data.source.remote.*
import com.dicoding.sahamiq.core.data.source.remote.network.*
import com.dicoding.sahamiq.core.data.source.remote.response.*
import com.dicoding.sahamiq.core.domain.model.*
import com.dicoding.sahamiq.core.domain.repository.*
import com.dicoding.sahamiq.core.utils.*
import kotlinx.coroutines.flow.*

class SahamTrendingRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISahamRepository {

    override fun getAllSahamTrending(): Flow<Resource<List<Saham>>> =
        object : NetworkBoundResource<List<Saham>, List<ResultsItemResponse>>() {
            override fun loadFromDB(): Flow<List<Saham>> =
                localDataSource.getAllSaham().map {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemResponse>>> =
                remoteDataSource.getAllSaham()

            override suspend fun saveCallResult(data: List<ResultsItemResponse>) {
                val sahamList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertSaham(sahamList)
            }

            override fun shouldFetch(data: List<Saham>?): Boolean =
                data.isNullOrEmpty()

        }.asFlow()

    override fun getFavoriteSahamTrending(): Flow<List<Saham>> =
        localDataSource.getFavoriteSaham().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteSahamTrending(saham: Saham, state: Boolean) {
        val sahamEntity = DataMapper.mapDomainToEntity(saham)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteSaham(sahamEntity, state)
        }
    }
}
