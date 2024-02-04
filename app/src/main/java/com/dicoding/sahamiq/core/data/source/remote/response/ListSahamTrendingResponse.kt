package com.dicoding.sahamiq.core.data.source.remote.response

import com.google.gson.annotations.*
import kotlinx.parcelize.*


data class ListSahamTrendingResponse(

    @field:SerializedName("data")
    val data: DataSahamResponse? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class DataSahamResponse(

    @field:SerializedName("results")
    val results: List<ResultsItemResponse>
)

data class CompanyResponse(

    @field:SerializedName("symbol")
    val symbol: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("logo")
    val logo: String? = null
)

data class ResultsItemResponse(

    @field:SerializedName("symbol")
    val symbol: String,

    @field:SerializedName("change")
    val change: Int? = null,

    @field:SerializedName("company")
    val company: CompanyResponse,

    @field:SerializedName("close")
    val close: Int? = null,

    @field:SerializedName("percent")
    val percent: Double? = null,

)