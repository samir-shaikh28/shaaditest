package com.droidtechlab.shaaditestui.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.shaaditestui.data.db.entities.DOBEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DOBEntityConverter {
    private val gson = Gson()
    private val type = object : TypeToken<DOBEntity>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): DOBEntity? {
        return if (json == null) {
            null
        } else gson.fromJson<DOBEntity>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: DOBEntity?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}