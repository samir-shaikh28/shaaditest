package com.droidtechlab.shaaditestui.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PictureObject(
    @SerializedName("large") val largeImage: String? = null,
    @SerializedName("medium") val mediumImage: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null
) : Parcelable