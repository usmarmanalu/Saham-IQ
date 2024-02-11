package com.dicoding.sahamiq.news

import androidx.lifecycle.*
import com.dicoding.sahamiq.core.domain.usecase.*

class NewsViewModel( useCase: SahamUseCase): ViewModel() {

    val news = useCase.getAllNews().asLiveData()

}