package com.droidtechlab.shaaditestui.data.db.entities.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    companion object {
        private val gson = Gson()
        private val type = object : TypeToken<Any>() { }.type

        @TypeConverter
        @JvmStatic
        fun stringToNestedData(json: String?): Any? {
            return if (json == null) {
                null
            } else gson.fromJson<Any>(json, type)
        }

        @TypeConverter
        @JvmStatic
        fun nestedDataToString(nestedData: Any?): String? {
            return if (nestedData == null) {
                null
            } else gson.toJson(nestedData, type)
        }
    }
}