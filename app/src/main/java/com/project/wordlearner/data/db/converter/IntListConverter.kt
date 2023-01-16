package com.project.wordlearner.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntListConverter {

    @TypeConverter
    fun fromString(value: String): List<Int> {
        val listType = object : TypeToken<List<String>>() {}.type

        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(value: List<Int>): String {
        return Gson().toJson(value)
    }
}