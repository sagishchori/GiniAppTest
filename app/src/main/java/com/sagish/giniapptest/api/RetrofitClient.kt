package com.sagish.giniapptest.api

import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL : String = "https://pastebin.com/"

    private val retrofit: Retrofit? = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(HttpUrl.parse(BASE_URL)!!)
        .build()

    fun<T> buildService(service : Class<T>) : T? {
        return retrofit?.create(service)
    }
}
