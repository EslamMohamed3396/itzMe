package com.itzme.data.models.baseResponse

import com.google.gson.annotations.SerializedName

class ErrorResponse {

    @SerializedName("Data")
    val `data`: Any? = null

    @SerializedName("ErrorCode")

    val errorCode: Int? = null

    @SerializedName("ErrorMessage")
    val errorMessage: String? = null
}