package com.itzme.data.models.account.changePassword.response

import com.google.gson.annotations.SerializedName
import com.itzme.data.models.baseResponse.IBaseResponse

data class ResponseChangePassword(
    @SerializedName("ErrorCode")
    override var errorCode: Int?,
    @SerializedName("ErrorMessage")
    override val errorMessage: String?,
    @SerializedName("Data")
    val `data`: Any?
) : IBaseResponse()