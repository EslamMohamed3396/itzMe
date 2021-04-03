package com.itzme.data.models.contact.removeContact.response


import com.google.gson.annotations.SerializedName

data class ResponseRemoveContact(
        @SerializedName("ErrorCode")
        val errorCode: Int?,
        @SerializedName("ErrorMessage")
        val errorMessage: String?,
        @SerializedName("Data")
        val `data`: Any?
)