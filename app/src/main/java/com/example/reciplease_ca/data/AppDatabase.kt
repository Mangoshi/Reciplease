package com.example.reciplease_ca.data

import android.content.Context
import androidx.room.*

// App's RoomDatabase for defining the database setup
@Database(entities = [MealEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // The meal Data Access Object defines the database interactions
    abstract fun mealDao(): MealDao?

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "meals.db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }
}