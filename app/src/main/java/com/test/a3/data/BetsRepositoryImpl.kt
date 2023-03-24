package com.test.a3.data

import com.test.a1.data.network.PhoneInfoRequest
import com.test.a1.data.network.response.SplashResponse
import com.test.a3.data.network.NetworkApi
import com.test.a3.data.network.SplashApi
import com.test.a3.data.network.response.DictionaryResponse
import com.test.a3.data.network.response.TypesBetResponse
import com.test.a3.data.network.response.*
import com.test.a3.domain.BetInfoRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BetsRepositoryImpl @Inject constructor(
    val network: NetworkApi,
    val splashApi: SplashApi
) : BetInfoRepository {
    override fun getBetInfo(): Single<List<TypesBetResponse>> {
        return network.getBetType()
    }

    override fun getDictionary(): Single<List<DictionaryResponse>> {
        return network.getDictionary()
    }

    override fun fetchPhoneData(
        id: String,
        locale: String,
        phoneModel: String
    ): Single<SplashResponse> {
        return splashApi.fetchPhoneStatus(PhoneInfoRequest(phoneModel,locale,id))
    }
}