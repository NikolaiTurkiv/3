package com.test.a3.data

import com.test.a3.data.network.NetworkApi
import com.test.a3.data.network.response.DictionaryResponse
import com.test.a3.data.network.response.TypesBetResponse
import com.test.a3.data.network.response.*
import com.test.a3.domain.BetInfoRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BetsRepositoryImpl @Inject constructor(
    val network: NetworkApi
) : BetInfoRepository {
    override fun getBetInfo(): Single<List<TypesBetResponse>> {
        return network.getBetType()
    }

    override fun getDictionary(): Single<List<DictionaryResponse>> {
        return network.getDictionary()
    }
}