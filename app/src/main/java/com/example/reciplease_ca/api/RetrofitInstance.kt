package com.example.reciplease_ca.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// API BASE_URL defines the part of the API's URL that never changes
private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

// RetrofitInstance object defining the settings & calls to be made
object RetrofitInstance {

    // Lazy loaded retrofit variable defining the RetrofitBuilder's config
    // Uses BASE_URL as the baseUrl
    // Uses Moshi as a converter factory
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    // List all categories retrofit implementation
    val GET_ALL_CATEGORIES: GETAllCategories by lazy {
        retrofit.create(GETAllCategories::class.java)
    }

    // List meals by category retrofit implementation
    val GET_MEALS_BY_CATEGORY: GETMealsByCategory by lazy {
        retrofit.create(GETMealsByCategory::class.java)
    }

    // List meal details by meal name retrofit implementation
    val GET_MEAL_BY_NAME: GETMealByName by lazy {
        retrofit.create(GETMealByName::class.java)
    }

    // List meal details by meal ID retrofit implementation
    val GET_MEAL_BY_ID: GETMealById by lazy {
        retrofit.create(GETMealById::class.java)
    }
}