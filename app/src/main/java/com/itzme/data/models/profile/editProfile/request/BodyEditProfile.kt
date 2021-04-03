package com.itzme.data.models.profile.editProfile.request


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class BodyEditProfile(
        @SerializedName("Name")
        val name: String?,
        @SerializedName("ImageBase64")
        val imageBase64: String?,
        @SerializedName("Email")
        val email: String?,
        @SerializedName("Bio")
        val bio: String?,
        @SerializedName("IsProfilePublic")
        val isProfilePublic: Boolean?
) : Parcelable