package com.droidtechlab.shaaditestui.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShaadiMatchesListResponse(
    @SerializedName("results") val results: List<MatchesList>? = null,
    @SerializedName("info") val info: InfoObject? = null
): Parcelable