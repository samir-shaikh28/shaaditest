package com.droidtechlab.shaaditestui.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchesList(
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("name") val nameObject: NameObject? = null,
    @SerializedName("location") val locationObject: LocationObject? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("cell") val cell: String? = null,
    @SerializedName("login") val loginObject: LoginObject? = null,
    @SerializedName("dob") val dobObject: DOBObject? = null,
    @SerializedName("registered") val registeredObject: RegisteredObject? = null,
    @SerializedName("id") val idObject: IDObject? = null,
    @SerializedName("picture") val pictureObject: PictureObject? = null,
    @SerializedName("nat") val NAT: String? = null
) : Parcelable
