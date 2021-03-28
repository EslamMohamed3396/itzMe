package com.itzme.data.models.account.changeEmail.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Data(
        @SerializedName("Email")
        val email: String?
) : Parcelable