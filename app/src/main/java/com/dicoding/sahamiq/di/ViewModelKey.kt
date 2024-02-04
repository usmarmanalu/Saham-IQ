package com.dicoding.sahamiq.di

import androidx.lifecycle.*
import dagger.*
import kotlin.reflect.*

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)

@Retention(AnnotationRetention.RUNTIME)

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)