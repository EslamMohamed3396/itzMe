package com.itzme.data.models.profile.editLink.request


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class BodyEditLink(
        @SerializedName("LinkType")
        val linkType: Int?,
        @SerializedName("Name")
        val name: String?,
        @SerializedName("Link")
        val link: String?,
        @SerializedName("IsHidden")
        val isHidden: Int?,
        @SerializedName("IsActive")
        val isActive: Int?,
        @SerializedName("Address")
        val address: String?,
        @SerializedName("Information")
        val information: String?,
        @SerializedName("BloodType")
        val bloodType: String?,
        @SerializedName("EmergencyContactName")
        val emergencyContactName: String?,
        @SerializedName("EmergencyContactPhone")
        val emergencyContactPhone: String?,
        @SerializedName("PetType")
        val petType: String?,
        @SerializedName("ImageUrl")
        val imageUrl: String?
) : Parcelable