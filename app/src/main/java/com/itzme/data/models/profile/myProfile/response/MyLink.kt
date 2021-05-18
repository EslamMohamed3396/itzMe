package com.itzme.data.models.profile.myProfile.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.itzme.data.models.baseResponse.BaseLink
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class MyLink(
        @SerializedName("LinkType")
        override val linkType: Int?,
        @SerializedName("LinkTypeName")
        override val linkTypeName: String?,
        @SerializedName("LinkPlaceholder")
        override val linkPlaceholder: String?,
        @SerializedName("LinkHint")
        override val linkHint: String?,
        @SerializedName("LinkValidation")
        override val linkValidation: String?,
        @SerializedName("LinkIconUrl")
        override val linkIconUrl: String?,
        @SerializedName("LinkPosition")
        override val linkPosition: Int?,
        @SerializedName("Name")
        override val name: String?,
        @SerializedName("Link")
        override val link: String?,
        @SerializedName("ImageUrl")
        override val imageUrl: String?,
        @SerializedName("IsHidden")
        override val isHidden: Boolean?,
        @SerializedName("IsActive")
        override val isActive: Boolean?,
        @SerializedName("Address")
        override val address: String?,
        @SerializedName("Information")
        override val information: String?,
        @SerializedName("BloodType")
        override val bloodType: String?,
        @SerializedName("EmergencyContactName")
        override val emergencyContactName: String?,
        @SerializedName("EmergencyContactPhone")
        override val emergencyContactPhone: String?,
        @SerializedName("PetType")
        override val petType: String?
) : Parcelable, BaseLink()