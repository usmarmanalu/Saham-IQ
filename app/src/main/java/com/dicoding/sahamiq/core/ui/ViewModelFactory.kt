package com.dicoding.sahamiq.core.ui

import androidx.lifecycle.*
import com.dicoding.sahamiq.di.*
import javax.inject.*

@Suppress("UNCHECKED_CAST")
@AppScope
class ViewModelFactory @Inject constructor(

    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}