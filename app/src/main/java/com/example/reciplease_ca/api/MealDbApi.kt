package com.example.reciplease_ca.api

import com.example.reciplease_ca.models.CategoryResponse
import com.example.reciplease_ca.models.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

// -- Retrofit API interfaces -- //

// www.themealdb.com/api/json/v1/1/categories.php
interface GETAllCategories {
    @GET("categories.php")
    suspend fun getAllCategories(): CategoryResponse
}

// www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
interface GETMealsByCategory {
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryName: String): MealResponse
}

// www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
interface GETMealByName {
    @GET("search.php")
    suspend fun getMealByName(@Query("s") searchQuery: String): MealResponse
}

// www.themealdb.com/api/json/v1/1/lookup.php?i=52772
interface GETMealById{
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") mealId: Int): MealResponse
}