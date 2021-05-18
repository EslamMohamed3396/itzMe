package com.itzme.data.models.metaData.response


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Data(
    @SerializedName("AppleStoreApplicationLink")
    val appleStoreApplicationLink: String?,
    @SerializedName("FacebookLink")
    val facebookLink: String?,
    @SerializedName("GooglePlayApplicationLink")
    val googlePlayApplicationLink: String?
) : Parcelable