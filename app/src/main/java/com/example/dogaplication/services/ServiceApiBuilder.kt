package com.example.dogaplication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceApiBuilder {
    private const val BASE_URL = "https://dog.ceo/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun create(): ApiDogsService{
        return retrofit.create(ApiDogsService::class.java)
    }
}