package com.project.wordlearner.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type

        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(value: List<String>): String {
        return Gson().toJson(value)
    }
}