package com.droidtechlab.shaaditestui.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisteredObject(
    @SerializedName("date") val date: String? = null,
    @SerializedName("age") val age: Int = 0
) : Parcelable