package com.droidtechlab.shaaditestui.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.shaaditestui.data.db.entities.LocationEntity
import com.droidtechlab.shaaditestui.data.db.entities.PictureEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LocationEntityConverter {
    private val gson = Gson()
    private val type = object : TypeToken<LocationEntity>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): LocationEntity? {
        return if (json == null) {
            null
        } else gson.fromJson<LocationEntity>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: LocationEntity?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}