// This is a primary building block in saving data to memory and persistent storage
package com.example.reciplease_ca.data

import com.example.reciplease_ca.NEW_NOTE_ID
import java.util.*

// "data" means there will be some properties used
data class NoteEntity(
    var id: Int,
    var date: Date,
    var text: String
) {
    constructor() : this(NEW_NOTE_ID, Date(), "")
    constructor(date: Date, text: String) : this(NEW_NOTE_ID, date, text)
}
