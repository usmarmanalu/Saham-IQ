package com.dicoding.sahamiq.core.data.source.remote

import android.util.*
import com.dicoding.sahamiq.core.*
import com.dicoding.sahamiq.core.data.source.remote.network.*
import com.dicoding.sahamiq.core.data.source.remote.response.*
import com.dicoding.sahamiq.core.utils.DataMapper.mapToResultsItemResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class RemoteDataSource constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getAllSaham(): Flow<ApiResponse<List<ResultsItemResponse>>> {
        return flow {
            try {
                val response = withContext(ioDispatcher) {
                    apiService.getList(BuildConfig.TOKEN)
                }
                val data = response.data?.results
                if (!data.isNullOrEmpty()) {
                    val mappedData = data.map { mapToResultsItemResponse(it) }
                    emit(ApiResponse.Success(mappedData))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(ioDispatcher)
    }


    fun getNews(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = withContext(ioDispatcher) {
                    apiService.getNews(BuildConfig.TOKEN)
                }
                val data = response.data?.results
                if (data != null) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(ioDispatcher)
    }
}
