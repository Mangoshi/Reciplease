package com.example.reciplease_ca.data

import androidx.room.TypeConverter

// Using this class to convert Date type to Long for storage on SQLite DB,
// and then back again once received.

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}