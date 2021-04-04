package com.itzme.data.models.profile.turnOnOffProfile.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Data(
        @SerializedName("IsProfilePrivate")
        val isProfilePrivate: Boolean?
) : Parcelable