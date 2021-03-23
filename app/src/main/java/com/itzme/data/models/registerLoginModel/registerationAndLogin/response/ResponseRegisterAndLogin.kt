package com.itzme.data.models.registerLoginModel.registerationAndLogin.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.itzme.data.models.baseResponse.IBaseResponse
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseRegisterAndLogin(
    @SerializedName("Data")
    val `data`: Data?,
    @SerializedName("ErrorCode")
    override var errorCode: Int?,
    @SerializedName("ErrorMessage")
    override val errorMessage: String?
) : Parcelable, IBaseResponse()