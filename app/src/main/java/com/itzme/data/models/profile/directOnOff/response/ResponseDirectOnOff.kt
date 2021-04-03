package com.itzme.data.models.profile.directOnOff.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseDirectOnOff(
        @SerializedName("ErrorCode")
        val errorCode: Int?,
        @SerializedName("ErrorMessage")
        val errorMessage: String?,
        @SerializedName("Data")
        val `data`: String?
) : Parcelable