package com.droidtechlab.shaaditestui.data.db.entities

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class PictureEntity(
    @ColumnInfo(name = "large") val large: String? = null,
    @ColumnInfo(name = "medium") val medium: String? = null,
    @ColumnInfo(name = "thumbnail") val thumbnail: String? = null
) :  Parcelable