package com.example.reciplease_ca.api

import com.example.reciplease_ca.data.MealEntity
import retrofit2.http.GET

interface MealApi {
    @GET("meals")
    suspend fun getMeals(): List<MealEntity>
}