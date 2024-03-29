package com.itzme.data.models.profile.myProfile.response


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class PetData(
    @SerializedName("Name")
    val name: String?,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Information")
    val information: String?,
    @SerializedName("EmergencyContactName")
    val emergencyContactName: String?,
    @SerializedName("EmergencyContactPhone")
    val emergencyContactPhone: String?,
    @SerializedName("IsActive")
    val isActive: Boolean?
) : Parcelable