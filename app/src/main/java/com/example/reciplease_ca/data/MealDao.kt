package com.example.reciplease_ca.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// Data Access Object: used when modifying / reading from our SQLite DB
// This DAO is being used to store meals the user "hearts"
@Dao
interface MealDao {

    // This function can be used either to insert a new meal, or update an existing meal,
    // if the primary keys of the row and entity match.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(meal: MealEntity)

    // Insert a bunch of meals, but if they already exist in the database, skip the new ones
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(meal: List<MealEntity>)

    // Select all meals from DB
    // Could use this to view heart list
    @Query("SELECT * FROM meals ORDER BY text ASC")
    fun getAll(): LiveData<List<MealEntity>>

    // Select a meal (by ID)
    @Query("SELECT * FROM meals WHERE id = :id")
    fun getMealById(id: Int): MealEntity?

    // Delete a meal (by ID)
    @Query("DELETE FROM meals WHERE id = :id")
    fun removeMeal(id: Int)

}