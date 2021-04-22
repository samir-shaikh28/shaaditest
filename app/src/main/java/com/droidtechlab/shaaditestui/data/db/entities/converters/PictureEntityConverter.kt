package com.droidtechlab.shaaditestui.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.shaaditestui.data.db.entities.PictureEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PictureEntityConverter {
    private val gson = Gson()
    private val type = object : TypeToken<PictureEntity>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): PictureEntity? {
        return if (json == null) {
            null
        } else gson.fromJson<PictureEntity>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: PictureEntity?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}
