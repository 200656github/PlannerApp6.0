package com.example.planner.data.local

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate): String = date.toString()

    @TypeConverter
    fun toLocalDate(value: String): LocalDate = LocalDate.parse(value)

    @TypeConverter
    fun fromNullableLocalDate(date: LocalDate?): String? = date?.toString()

    @TypeConverter
    fun toNullableLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }
}
