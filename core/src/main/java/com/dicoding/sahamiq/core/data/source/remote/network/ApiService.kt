package com.dicoding.sahamiq.core.data.source.remote.network

import com.dicoding.sahamiq.core.data.source.remote.response.*
import retrofit2.*
import retrofit2.http.*

interface ApiService {
    @GET("stock/idx/trending")
    suspend fun getList(
        @Header("X-API-KEY") token: String
    ): ListSahamTrendingResponse

    @GET("stock/idx/news")
    suspend fun getNews(
        @Header("X-API-KEY") token: String
    ): NewsResponse
}