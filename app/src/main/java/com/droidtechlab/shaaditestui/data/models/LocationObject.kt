package com.droidtechlab.shaaditestui.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationObject(
    @SerializedName("street") val street: StreetObject? = null,
    @SerializedName("coordinates") val coordinates: CoordinatesObject? = null,
    @SerializedName("timezone") val timezone: TimezoneObject? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("postcode") val postcode: Int = -1
): Parcelable


@Parcelize
data class StreetObject(
    @SerializedName("name") val name: String? = null,
    @SerializedName("number") val number: Int = -1,
): Parcelable

@Parcelize
data class CoordinatesObject(
    @SerializedName("latitude") val latitude: String? = null,
    @SerializedName("longitude") val longitude: String? = null
): Parcelable

@Parcelize
data class TimezoneObject(
    @SerializedName("offset") val offset: String? = null,
    @SerializedName("description") val description: String? = null
): Parcelable