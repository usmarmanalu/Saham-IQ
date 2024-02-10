package com.dicoding.sahamiq.core.data.source.local.room

import androidx.room.*
import com.dicoding.sahamiq.core.data.source.local.entity.*
import com.google.gson.*

class CompanyConverter {

    @TypeConverter
    fun fromCompany(company: CompanyEntity?): String? {
        return Gson().toJson(company)
    }

    @TypeConverter
    fun toCompany(json: String?): CompanyEntity? {
        return Gson().fromJson(json, CompanyEntity::class.java)
    }
}
