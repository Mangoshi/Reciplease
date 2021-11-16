package com.example.reciplease_ca.data

import android.content.Context
import androidx.room.*

@Database(entities = [MealEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

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