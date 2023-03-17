package com.test.a3.domain

import com.test.a3.domain.BetInfoRepository
import javax.inject.Inject

class BetUseCase @Inject constructor(
    private val repository: BetInfoRepository
) {
    val betType = repository.getBetInfo()
    val dictionary = repository.getDictionary()
}
