package com.itzme.data.models.contact.myContact.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.itzme.data.models.baseResponse.IBaseResponse
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseMyContact(
        @SerializedName("Data")
        val `data`: List<Data>?,
        @SerializedName("ErrorCode")
        override var errorCode: Int?,
        @SerializedName("ErrorMessage")
        override val errorMessage: String?,
) : Parcelable, IBaseResponse()