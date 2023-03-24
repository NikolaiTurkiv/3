package com.test.a3.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a1.data.network.response.SplashResponse
import com.test.a3.domain.BetUseCase
import com.test.a3.domain.OptionsUseCase

import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val betUseCase: BetUseCase,
    private val optionsUseCase: OptionsUseCase
): ViewModel() {

    val id = optionsUseCase.id

    private val _response = MutableLiveData<SplashResponse>()
    val response: LiveData<SplashResponse>
        get() = _response

    fun saveId(id:String){
        optionsUseCase.saveId(id)
    }

    fun fetchPhoneStatus(
        phoneName: String,
        locale: String,
        id: String,
    ) {
        Log.d("fetchPhoneStatus", phoneName)
        Log.d("fetchPhoneStatus", locale)
        Log.d("fetchPhoneStatus", id)

        betUseCase.fetchPhoneData(phoneName, locale, id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                Log.d("fetchPhoneStatus_res", it.url)

                _response.postValue(it)
            }, {
                Log.d("fetchPhoneStatus", it.message.toString())
            })
    }

}