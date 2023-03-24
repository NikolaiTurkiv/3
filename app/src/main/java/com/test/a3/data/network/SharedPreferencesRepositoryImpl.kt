package com.test.a3.data.network

import android.content.Context
import com.test.a3.R
import com.test.a3.domain.SharedPreferencesRepository
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(context: Context) :
    SharedPreferencesRepository {

    companion object {
        private const val DAGGER_SHARED_PREF = "DAGGER_SHARED_PREF"
        private const val DARK_THEME = "DARK_THEME"
        private const val WALLPAPER = "WALLPAPER"
        private const val ID = "ID"

    }

    private var sharedPreferences =
        context.getSharedPreferences(DAGGER_SHARED_PREF, Context.MODE_PRIVATE)

    override var isDarkTheme: Boolean
        get() = sharedPreferences.getBoolean(DARK_THEME, false)
        set(value) {
            sharedPreferences.edit().putBoolean(DARK_THEME, value).apply()
        }

    override val currentId: String
        get() = sharedPreferences.getString(ID,"") ?: ""

    override fun saveTheme(isDark: Boolean) {
        sharedPreferences.edit().putBoolean(DARK_THEME,isDark).apply()
    }


    override fun saveID(id: String) {
        sharedPreferences.edit().putString(ID,id).apply()
    }
}
