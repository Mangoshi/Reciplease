package com.example.reciplease_ca.api

import com.example.reciplease_ca.models.CategoryResponse
import com.example.reciplease_ca.models.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GETAllCategories {
    @GET("categories.php")
    suspend fun getAllCategories(): CategoryResponse
}

interface GETMealsByCategory {
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryName: String): MealResponse
}

interface GETMealByName {
    @GET("search.php")
    suspend fun getMealByName(@Query("s") searchQuery: String): MealResponse
}

interface GETMealById{
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") mealId: Int): MealResponse
}