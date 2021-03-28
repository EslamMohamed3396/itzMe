package com.itzme.data.models.account.registerationAndLogin.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Data(
    @SerializedName("LinkName")
    val linkName: String?,
    @SerializedName("Email")
    val email: String?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("ImageUrl")
    val imageUrl: String?,
    @SerializedName("IsExternal")
    val isExternal: Boolean?,
    @SerializedName("IsProfilePrivate")
    val isProfilePrivate: Boolean?,
    @SerializedName("Gender")
    val gender: String?,
    @SerializedName("DateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("Bio")
    val bio: String?,
    @SerializedName("IsDirectOn")
    val isDirectOn: Boolean?,
    @SerializedName("NumberOfViews")
    val numberOfViews: Int?,
    @SerializedName("PhoneNumber")
    val phoneNumber: String?,
    @SerializedName("DirectType")
    val directType: String?,
    @SerializedName("Token")
    val token: String?
) : Parcelable