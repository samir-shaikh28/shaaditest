package com.droidtechlab.shaaditestui.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.droidtechlab.shaaditestui.data.db.entities.converters.Converters
import kotlinx.parcelize.Parcelize

@Parcelize
class LocationEntity(
    @ColumnInfo(name = "city") val city: String? = null,
    @ColumnInfo(name = "state") val state: String? = null,
    @ColumnInfo(name = "country") val country: String? = null,
    @ColumnInfo(name = "postcode") val postcode: String? = null,
    @ColumnInfo(name = "street") @TypeConverters(Converters::class) val street: StreetEntity? = null
) :  Parcelable


@Parcelize
class StreetEntity(
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "number") val number: Int = 0
) : Parcelable

