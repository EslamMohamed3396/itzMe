package com.itzme.data.models.account.registerationAndLogin.request


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class BodyLogin(
    @SerializedName("Provider")
    val email: String?,
    @SerializedName("Password")
    val password: String?
) : Parcelable