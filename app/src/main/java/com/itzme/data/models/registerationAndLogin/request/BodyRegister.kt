package com.itzme.data.models.registerationAndLogin.request


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class BodyRegister(
    @SerializedName("Name")
    val name: String?,
    @SerializedName("Email")
    val email: String?,
    @SerializedName("Password")
    val password: String?
) : Parcelable