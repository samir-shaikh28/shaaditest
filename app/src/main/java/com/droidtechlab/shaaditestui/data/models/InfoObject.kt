package com.droidtechlab.shaaditestui.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InfoObject(
    @SerializedName("seed") val seed: String? = null,
    @SerializedName("results") val resultsCount: Int = 0,
    @SerializedName("page") val page: Int = 0,
    @SerializedName("version") val version: String? = null
): Parcelable
