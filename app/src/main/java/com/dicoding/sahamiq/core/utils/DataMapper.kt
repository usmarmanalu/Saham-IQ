package com.dicoding.sahamiq.core.utils

import com.dicoding.sahamiq.core.data.source.local.entity.CompanyEntity
import com.dicoding.sahamiq.core.data.source.local.entity.SahamTrendingEntity
import com.dicoding.sahamiq.core.data.source.remote.response.*
import com.dicoding.sahamiq.core.domain.model.*

object DataMapper {

    fun mapResponseToEntities(input: List<ResultsItemResponse>): List<SahamTrendingEntity> =
        input.map {
            SahamTrendingEntity(
                symbol = it.symbol,
                change = it.change,
                company = mapCompanyToDomain(it.company),
                close = it.close,
                percent = it.percent,
                isFavorite = false
            )
        }

    fun mapEntitiesToDomain(input: List<SahamTrendingEntity>): List<Saham> =
        input.map {
            Saham(
                symbol = it.symbol,
                change = it.change,
                company = mapCompanyEntityToDomain(it.company),
                close = it.close,
                percent = it.percent,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Saham): SahamTrendingEntity =
        SahamTrendingEntity(
            symbol = input.symbol,
            change = input.change,
            company = mapCompanyToEntity(input.company),
            close = input.close,
            percent = input.percent,
            isFavorite = input.isFavorite ?: false
        )

    private fun mapCompanyToDomain(companyResponse: CompanyResponse?): CompanyEntity {
        return CompanyEntity(
            symbol = companyResponse?.symbol.orEmpty(),
            name = companyResponse?.name.orEmpty(),
            logo = companyResponse?.logo.orEmpty()
        )
    }

    private fun mapCompanyEntityToDomain(companyEntity: CompanyEntity?): Company {
        return Company(
            symbol = companyEntity?.symbol.orEmpty(),
            name = companyEntity?.name.orEmpty(),
            logo = companyEntity?.logo.orEmpty()
        )
    }

    private fun mapCompanyToEntity(company: Company?): CompanyEntity {
        return CompanyEntity(
            symbol = company?.symbol.orEmpty(),
            name = company?.name.orEmpty(),
            logo = company?.logo.orEmpty()
        )
    }
}

