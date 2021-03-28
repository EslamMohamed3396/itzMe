package com.itzme.data.models.account.changePassword.request

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class BodyChangePassword(
        @SerializedName("OldPassword")
        val oldPassword: String?,
        @SerializedName("NewPassword")
        val newPassword: String?
) : Parcelable