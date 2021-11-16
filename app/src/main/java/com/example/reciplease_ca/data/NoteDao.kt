package com.example.reciplease_ca.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    // This function can be used either to insert a new note, or update an existing note,
    // if the primary keys of the row and entity match.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    // Inserting a bunch of notes, but if they already exist in the database, throw away the new ones
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(note: List<NoteEntity>)

    // Can use this to observe database table and use reactive programming so I can update the UI automatically as it changes
    @Query("SELECT * FROM notes ORDER BY date ASC")
    fun getAll(): LiveData<List<NoteEntity>>

    // Single select using ID parameter
    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity?
}