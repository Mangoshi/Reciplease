package com.example.reciplease_ca.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val GET_ALL_CATEGORIES: GETAllCategories by lazy {
        retrofit.create(GETAllCategories::class.java)
    }

    val GET_MEALS_BY_CATEGORY: GETMealsByCategory by lazy {
        retrofit.create(GETMealsByCategory::class.java)
    }

    val GET_MEAL_BY_NAME: GETMealByName by lazy {
        retrofit.create(GETMealByName::class.java)
    }

    val GET_MEAL_BY_ID: GETMealById by lazy {
        retrofit.create(GETMealById::class.java)
    }
}