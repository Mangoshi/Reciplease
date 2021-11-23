package com.example.reciplease_ca.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
object RetrofitInstance {

    //  Using the Retrofit builder to build a retrofit object using a Moshi converter.
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val api: MealApi by lazy {
        retrofit.create(MealApi::class.java)
    }
}