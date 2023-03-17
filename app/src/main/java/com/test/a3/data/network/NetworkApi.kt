package com.test.a3.data.network

import com.test.a3.data.network.response.DictionaryResponse
import com.test.a3.data.network.response.TypesBetResponse
import com.test.a3.data.network.response.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NetworkApi {

    companion object {
        private const val  DICTIONARY  = "dictionary.json"
        private const val  TYPE_BET  = "types_of_bet.json"
    }

    @GET(DICTIONARY)
    fun getDictionary(): Single<List<DictionaryResponse>>

    @GET(TYPE_BET)
    fun getBetType(): Single<List<TypesBetResponse>>

}
