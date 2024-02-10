package com.dicoding.sahamiq.core.utils

import androidx.appcompat.app.*

enum class DarkMode(val value: Int) {

    FOLLOW_SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    ON(AppCompatDelegate.MODE_NIGHT_YES),
    OFF(AppCompatDelegate.MODE_NIGHT_NO)
}