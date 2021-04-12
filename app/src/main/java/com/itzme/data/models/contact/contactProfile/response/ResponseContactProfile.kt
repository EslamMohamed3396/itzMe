package com.itzme.data.models.contact.contactProfile.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseContactProfile(
        @SerializedName("ErrorCode")
        val errorCode: Int?,
        @SerializedName("ErrorMessage")
        val errorMessage: String?,
        @SerializedName("Data")
        val `data`: Data?
) : Parcelable