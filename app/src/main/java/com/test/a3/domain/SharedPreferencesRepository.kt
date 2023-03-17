package com.test.a3.domain

interface SharedPreferencesRepository {
    var isDarkTheme: Boolean

    fun saveTheme(isDark: Boolean)
}