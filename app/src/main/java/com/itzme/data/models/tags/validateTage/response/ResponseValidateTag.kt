package com.itzme.data.models.tags.validateTage.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseValidateTag(
        @SerializedName("ErrorCode")
        val errorCode: Int?,
        @SerializedName("ErrorMessage")
        val errorMessage: String?,
        @SerializedName("Data")
        val `data`: Int?
) : Parcelable