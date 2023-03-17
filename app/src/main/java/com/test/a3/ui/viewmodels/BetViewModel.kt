package com.test.a3.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a3.data.network.response.DictionaryResponse
import com.test.a3.data.network.response.TypesBetResponse
import com.test.a3.domain.BetUseCase
import com.test.a3.domain.OptionsUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BetViewModel @Inject constructor(
    private val betUseCase: BetUseCase,
    private val optionsUseCase: OptionsUseCase,
) : ViewModel() {

    val isDarkTheme = optionsUseCase.isDarkTheme

    fun changeTheme(isDark: Boolean) {
        optionsUseCase.saveTheme(isDark)
    }

    private val _betTypeListLD = MutableLiveData<List<TypesBetResponse>>()
    val betTypeListLD: LiveData<List<TypesBetResponse>>
        get() = _betTypeListLD

    private val _betDictionaryListLD = MutableLiveData<List<DictionaryResponse>>()
    val betDictionaryListLD: LiveData<List<DictionaryResponse>>
        get() = _betDictionaryListLD

    fun getBetTypes() {
        betUseCase.betType
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                       _betTypeListLD.postValue(it)
            },{
                Log.d("GET_BET_TYPES_ERROR",it.message.toString())
            })
    }

    fun getDictionary() {
        betUseCase.dictionary
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                       _betDictionaryListLD.postValue(it)
            },{
                Log.d("GET_DICTIONARY_ERROR",it.message.toString())
            })
    }

}
