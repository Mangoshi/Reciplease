package com.example.reciplease_ca.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {

    // This function can be used either to insert a new meal, or update an existing meal,
    // if the primary keys of the row and entity match.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(meal: MealEntity)

    // Inserting a bunch of meals, but if they already exist in the database, throw away the new ones
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(meal: List<MealEntity>)

    // Can use this to observe database table and use reactive programming so I can update the UI automatically as it changes
    @Query("SELECT * FROM meals ORDER BY date ASC")
    fun getAll(): LiveData<List<MealEntity>>

    // Single select using ID parameter
    @Query("SELECT * FROM meals WHERE id = :id")
    fun getmMalById(id: Int): MealEntity?
}