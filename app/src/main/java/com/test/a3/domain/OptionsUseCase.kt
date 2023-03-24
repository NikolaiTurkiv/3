package com.test.a3.domain

import javax.inject.Inject

class OptionsUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    var isDarkTheme = sharedPreferencesRepository.isDarkTheme
    val id = sharedPreferencesRepository.currentId


    fun saveTheme(isDark: Boolean){
        sharedPreferencesRepository.saveTheme(isDark)
    }

    fun saveId(id:String){
        sharedPreferencesRepository.saveID(id)
    }

}