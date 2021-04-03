package com.itzme.data.models.notification.response


import com.google.gson.annotations.SerializedName


data class ResponsePutToken(
        @SerializedName("ErrorCode")
        val errorCode: Int?,
        @SerializedName("ErrorMessage")
        val errorMessage: String?,
        @SerializedName("Data")
        val `data`: Any?
)