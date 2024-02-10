package com.dicoding.sahamiq.settings

import android.content.*
import android.content.res.*
import android.os.*
import android.provider.*
import androidx.appcompat.app.*
import androidx.preference.*
import com.dicoding.sahamiq.R
import com.dicoding.sahamiq.core.utils.*
import java.util.*

@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val switchDarkMode: ListPreference? = findPreference(getString(R.string.pref_key_dark))
            switchDarkMode?.setOnPreferenceChangeListener { _, newValue ->
                val stringValue = newValue.toString()
                if (stringValue == getString(R.string.pref_dark_follow_system)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        updateTheme(DarkMode.FOLLOW_SYSTEM.value)
                    } else {
                        updateTheme(DarkMode.ON.value)
                    }
                } else if (stringValue == getString(R.string.pref_dark_off)) {
                    updateTheme(DarkMode.OFF.value)
                } else {
                    updateTheme(DarkMode.ON.value)
                }
                true
            }

            val switchLanguage: ListPreference? = findPreference(getString(R.string.pref_key_language))
            switchLanguage?.setOnPreferenceChangeListener { _, newValue ->
                val language = newValue as String
                setLocale(language)
                requireActivity().recreate()
                true
            }
        }

        private fun setLocale(language: String) {
            val locale = Locale(language)
            Locale.setDefault(locale)
            val config = Configuration(resources.configuration)
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
            saveLanguagePref(language)
        }

        private fun saveLanguagePref(language: String) {
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("Language", language)
            editor.apply()
        }

        private fun updateTheme(mode: Int): Boolean {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}