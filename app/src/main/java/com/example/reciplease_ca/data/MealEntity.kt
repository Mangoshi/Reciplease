// This is a primary building block in saving data to memory and persistent storage
package com.example.reciplease_ca.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reciplease_ca.NEW_MEAL_ID
import java.util.*

// Defining the structure of the app's database table
// "data" in "data class" means there will be some properties used by this class
@Entity(tableName = "meals")
data class MealEntity(
    // Auto generate the primary key
    @PrimaryKey(autoGenerate = true)
    // Class variables
    var id: Int,
    var text: String
) {
    // Constructors
    constructor() : this(NEW_MEAL_ID, "")
    constructor(text: String) : this(NEW_MEAL_ID, text)
}
