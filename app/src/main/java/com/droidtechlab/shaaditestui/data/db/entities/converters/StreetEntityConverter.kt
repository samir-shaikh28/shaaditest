package com.droidtechlab.shaaditestui.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.shaaditestui.data.db.entities.StreetEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StreetEntityConverter {
    private val gson = Gson()
    private val type = object : TypeToken<StreetEntity>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): StreetEntity? {
        return if (json == null) {
            null
        } else gson.fromJson<StreetEntity>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: StreetEntity?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}