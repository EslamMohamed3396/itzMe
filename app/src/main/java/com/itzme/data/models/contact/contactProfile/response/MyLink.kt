package com.itzme.data.models.contact.contactProfile.response


import com.google.gson.annotations.SerializedName


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
    val petType: String?
)