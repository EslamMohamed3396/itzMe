package com.itzme.data.models.account.resetPassword.request


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class BodyResetPassword(
    @SerializedName("Key")
    val key: String?,
    @SerializedName("Password")
    val password: String?
) : Parcelable