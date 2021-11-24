package com.example.reciplease_ca.api

import com.example.reciplease_ca.models.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php")
    suspend fun getMeals(@Query("s") searchQuery: String): MealResponse
}