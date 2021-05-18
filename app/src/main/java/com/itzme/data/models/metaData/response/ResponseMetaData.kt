package com.itzme.data.models.metaData.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.itzme.data.models.baseResponse.IBaseResponse
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseMetaData(
        @SerializedName("ErrorCode")
        override val errorCode: Int?,
        @SerializedName("ErrorMessage")
        override val errorMessage: String?,
        @SerializedName("Data")
        val `data`: Data?
) : Parcelable, IBaseResponse()