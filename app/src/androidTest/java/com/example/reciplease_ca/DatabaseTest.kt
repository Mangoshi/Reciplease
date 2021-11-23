package com.example.reciplease_ca

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.reciplease_ca.data.AppDatabase
import com.example.reciplease_ca.data.MealDao
import com.example.reciplease_ca.data.MealEntity
import com.example.reciplease_ca.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: MealDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.mealDao()!! // has to be non-null
    }

    @Test
    fun createMeals() {
        dao.insertAll(SampleDataProvider.getMeals())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getMeals().size)
    }

    @Test
    fun insertMeal() {
        val meal = MealEntity()
        meal.name = "some text"
        dao.insertMeal(meal)
        val savedMeal = dao.getMealById(1)
        assertEquals(savedMeal?.id ?: 0,1)
    }

    @After
    fun closeDb() {
        database.close()
    }
}