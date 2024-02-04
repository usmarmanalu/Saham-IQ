package com.dicoding

import android.app.*
import com.dicoding.sahamiq.core.di.*
import com.dicoding.sahamiq.di.*

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}