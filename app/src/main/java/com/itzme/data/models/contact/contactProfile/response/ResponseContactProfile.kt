package com.itzme.data.models.contact.contactProfile.response


import com.google.gson.annotations.SerializedName


data class ResponseContactProfile(
    @SerializedName("ErrorCode")
    val errorCode: Int?,
    @SerializedName("ErrorMessage")
    val errorMessage: String?,
    @SerializedName("Data")
    val `data`: Data?
)