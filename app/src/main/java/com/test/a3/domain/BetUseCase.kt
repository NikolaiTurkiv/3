package com.test.a3.domain

import com.test.a1.data.network.response.SplashResponse
import com.test.a3.domain.BetInfoRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BetUseCase @Inject constructor(
    private val repository: BetInfoRepository,
) {
    val betType = repository.getBetInfo()
    val dictionary = repository.getDictionary()

    fun fetchPhoneData(id: String, locale: String, phoneModel: String): Single<SplashResponse> {
        return repository.fetchPhoneData(id, locale, phoneModel)
    }

}
