package com.dicoding.sahamiq.core.data.source.remote.network

import com.dicoding.sahamiq.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("stock/idx/trending")
    suspend fun getList(
        @Header("X-API-KEY") token: String
    ): ListSahamTrendingResponse
}