package com.itzme.data.models.account.forgetPassword.response


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Data(
    @SerializedName("Email")
    val email: String?,
    @SerializedName("Code")
    val code: String?,
) : Parcelable