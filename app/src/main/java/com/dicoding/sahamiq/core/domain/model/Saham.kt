package com.dicoding.sahamiq.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.*

@Parcelize
data class Saham(
    val symbol: String,
    val change: Int?,
    val company: Company,
    val close: Int?,
    val percent: Double?,
    val isFavorite: Boolean? = null
): Parcelable

@Parcelize
data class Company(
    val symbol: String,
    val name: String?,
    val logo: String?
): Parcelable
