package com.itzme.data.models.contact.contactProfile.response


import com.google.gson.annotations.SerializedName


data class FindMeData(
    @SerializedName("Name")
    val name: String?,
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
    @SerializedName("IsActive")
    val isActive: Boolean?
)