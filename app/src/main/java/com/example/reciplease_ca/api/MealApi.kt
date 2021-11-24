package com.example.reciplease_ca.api

import com.example.reciplease_ca.models.Meal
import retrofit2.http.GET

interface MealApi {
    @GET("random")
    suspend fun getMeals(): List<Meal>
}