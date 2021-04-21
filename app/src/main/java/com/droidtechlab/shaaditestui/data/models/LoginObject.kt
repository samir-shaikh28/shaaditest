package com.droidtechlab.shaaditestui.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginObject(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("salt") val salt: String? = null,
    @SerializedName("md5") val md5: String? = null,
    @SerializedName("sha1") val sha1: String? = null,
    @SerializedName("sha256") val sha256: String? = null
) : Parcelable