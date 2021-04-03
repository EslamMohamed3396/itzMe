package com.itzme.data.models.notification.request


import com.google.gson.annotations.SerializedName


data class BodyAddToken(
        @SerializedName("PushToken")
        val pushToken: String?,
        @SerializedName("OS")
        val oS: Int? = 0,
        @SerializedName("IsWorker")
        val isWorker: Boolean? = false
)