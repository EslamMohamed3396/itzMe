package com.itzme.data.models.contact.contactProfile.response


import com.google.gson.annotations.SerializedName


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
)