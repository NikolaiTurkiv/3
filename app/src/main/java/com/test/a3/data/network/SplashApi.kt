package com.test.a3.data.network

import com.test.a1.data.network.PhoneInfoRequest
import com.test.a1.data.network.response.SplashResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SplashApi {
    @POST("splash.php")
    fun fetchPhoneStatus(
        @Body request: PhoneInfoRequest
    ): Single<SplashResponse>
}