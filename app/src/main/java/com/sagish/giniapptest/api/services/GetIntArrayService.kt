package com.sagish.giniapptest.api.services

import com.sagish.giniapptest.models.Numbers
import retrofit2.http.GET

interface GetIntArrayService {

    @GET("raw/8wJzytQX")
    suspend fun getIntArray() : Numbers
}