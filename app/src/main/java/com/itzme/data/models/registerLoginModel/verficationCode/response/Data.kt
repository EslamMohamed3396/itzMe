package com.itzme.data.models.registerLoginModel.verficationCode.response


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Data(
    @SerializedName("Key")
    val key: String?
) : Parcelable