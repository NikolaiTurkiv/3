package com.test.a3.domain

import javax.inject.Inject

class OptionsUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    var isDarkTheme = sharedPreferencesRepository.isDarkTheme

    fun saveTheme(isDark: Boolean){
        sharedPreferencesRepository.saveTheme(isDark)
    }

}