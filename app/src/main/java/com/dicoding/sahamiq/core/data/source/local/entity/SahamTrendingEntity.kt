package com.dicoding.sahamiq.core.data.source.local.entity

import androidx.room.*

@Entity(tableName = "saham_trending")
data class SahamTrendingEntity(

    @PrimaryKey
    @ColumnInfo(name = "symbol")
    val symbol: String,

    @ColumnInfo(name = "change")
    val change: Int?,

    @ColumnInfo(name = "company_symbol")
    val company: CompanyEntity?,

    @ColumnInfo(name = "close")
    val close: Int?,

    @ColumnInfo(name = "percent")
    val percent: Double?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)

@Entity(tableName = "company")
data class CompanyEntity(

    @PrimaryKey
    @ColumnInfo(name = "symbol")
    val symbol: String,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "logo")
    val logo: String?
)

