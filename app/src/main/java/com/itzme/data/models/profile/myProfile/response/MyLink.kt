package com.itzme.data.models.profile.myProfile.response


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class MyLink(
    @SerializedName("LinkType")
    val linkType: Int?,
    @SerializedName("LinkTypeName")
    val linkTypeName: String?,
    @SerializedName("LinkIconUrl")
    val linkIconUrl: String?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("Link")
    val link: String?,
    @SerializedName("ImageUrl")
    val imageUrl: String?,
    @SerializedName("IsHidden")
    val isHidden: Boolean?,
    @SerializedName("IsActive")
    val isActive: Boolean?,
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
    val petType: Int?
) : Parcelable