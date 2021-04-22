package com.droidtechlab.shaaditestui.data.db.entities

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class NameEntity(
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "first") val first: String? = null,
    @ColumnInfo(name = "last") val last: String? = null
):  Parcelable