package com.amo.motherfinancetest.datasource.remote

import com.amo.motherfinancetest.model.Banks
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("banks")
    fun getBanks(): Deferred<Response<Banks>>

}