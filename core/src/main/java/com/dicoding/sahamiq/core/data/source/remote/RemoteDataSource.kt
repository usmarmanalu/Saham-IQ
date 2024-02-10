package com.dicoding.sahamiq.core.data.source.remote

import android.util.*
import com.dicoding.sahamiq.core.*
import com.dicoding.sahamiq.core.data.source.remote.network.*
import com.dicoding.sahamiq.core.data.source.remote.response.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class RemoteDataSource constructor(private val apiService: ApiService) {

    suspend fun getAllSaham(): Flow<ApiResponse<List<ResultsItemResponse>>> {
        return flow {
            try {
                val response = apiService.getList(BuildConfig.TOKEN)
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
        }.flowOn(Dispatchers.IO)
    }


    private fun mapToResultsItemResponse(dataSaham: ResultsItemResponse): ResultsItemResponse {
        return dataSaham.copy(
            symbol = dataSaham.symbol,
            change = dataSaham.change,
            company = dataSaham.company,
            close = dataSaham.close,
            percent = dataSaham.percent,
        )
    }

}
