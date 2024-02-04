package com.dicoding.sahamiq.core.domain.usecase

import com.dicoding.sahamiq.core.domain.model.*
import com.dicoding.sahamiq.core.domain.repository.*
import javax.inject.*

class SahamInteractor @Inject constructor(

    private val sahamRepository: ISahamRepository
) : SahamUseCase {
    override fun getAllSaham() = sahamRepository.getAllSahamTrending()

    override fun getFavoriteSaham() = sahamRepository.getFavoriteSahamTrending()

    override fun setFavoriteSaham(saham: Saham, state: Boolean) =
        sahamRepository.setFavoriteSahamTrending(saham, state)

}