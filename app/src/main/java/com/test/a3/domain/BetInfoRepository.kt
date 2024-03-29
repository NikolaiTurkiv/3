package com.test.a3.domain

import com.test.a1.data.network.response.SplashResponse
import com.test.a3.data.network.response.DictionaryResponse
import com.test.a3.data.network.response.TypesBetResponse
import com.test.a3.data.network.response.*
import io.reactivex.rxjava3.core.Single

interface BetInfoRepository {
    fun getBetInfo(): Single<List<TypesBetResponse>>

    fun getDictionary(): Single<List<DictionaryResponse>>

    fun fetchPhoneData(id: String, locale: String, phoneModel: String): Single<SplashResponse>


}
