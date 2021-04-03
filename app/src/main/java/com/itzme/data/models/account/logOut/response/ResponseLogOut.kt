package com.itzme.data.models.account.logOut.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseLogOut(
        @SerializedName("Message")
        val message: Int?
) : Parcelable