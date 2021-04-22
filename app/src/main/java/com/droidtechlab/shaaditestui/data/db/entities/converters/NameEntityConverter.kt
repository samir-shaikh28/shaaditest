package com.droidtechlab.shaaditestui.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.shaaditestui.data.db.entities.NameEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object NameEntityConverter {
    private val gson = Gson()
    private val type = object : TypeToken<NameEntity>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): NameEntity? {
        return if (json == null) {
            null
        } else gson.fromJson<NameEntity>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: NameEntity?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}
