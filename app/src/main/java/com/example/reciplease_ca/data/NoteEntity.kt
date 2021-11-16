// This is a primary building block in saving data to memory and persistent storage
package com.example.reciplease_ca.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reciplease_ca.NEW_NOTE_ID
import java.util.*

// Defining the structure of the app's database table
// "data" in "data class" means there will be some properties used by this class
@Entity(tableName = "notes")
data class NoteEntity(@PrimaryKey(autoGenerate = true)
                      var id: Int,
                      var date: Date,
                      var text: String
) {
    constructor() : this(NEW_NOTE_ID, Date(), "")
    constructor(date: Date, text: String) : this(NEW_NOTE_ID, date, text)
}
