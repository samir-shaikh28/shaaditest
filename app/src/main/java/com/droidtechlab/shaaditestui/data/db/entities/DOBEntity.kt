package com.droidtechlab.shaaditestui.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class DOBEntity(
    @ColumnInfo(name = "date") val date: String? = null,
    @ColumnInfo(name = "age") val age: Int = 0
) : Parcelable