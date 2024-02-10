package com.dicoding

import android.app.*
import androidx.appcompat.app.*
import androidx.preference.*
import com.dicoding.sahamiq.R
import com.dicoding.sahamiq.core.di.*
import com.dicoding.sahamiq.core.utils.*
import com.dicoding.sahamiq.di.*
import org.koin.android.ext.koin.*
import org.koin.core.context.*
import org.koin.core.logger.*
import java.util.*


open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }

        val preference = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        preference.getString(
            getString(R.string.pref_key_dark),
            getString(R.string.pref_dark_follow_system)
        )?.apply {
            val mode = DarkMode.valueOf(this.uppercase(Locale.US))
            AppCompatDelegate.setDefaultNightMode(mode.value)
        }
    }
}