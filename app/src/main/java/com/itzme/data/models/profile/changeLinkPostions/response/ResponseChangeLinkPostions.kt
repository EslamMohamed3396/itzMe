package com.itzme.data.models.profile.changeLinkPostions.response


import com.google.gson.annotations.SerializedName


data class ResponseChangeLinkPostions(
        @SerializedName("ErrorCode")
        val errorCode: Int?,
        @SerializedName("ErrorMessage")
        val errorMessage: String?,
        @SerializedName("Data")
        val `data`: Any?
)