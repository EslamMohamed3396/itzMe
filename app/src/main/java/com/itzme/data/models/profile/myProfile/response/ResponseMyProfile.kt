package com.itzme.data.models.profile.myProfile.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseMyProfile(
        @SerializedName("LinkName")
        val linkName: String?,
        @SerializedName("Name")
        val name: String?,
        @SerializedName("Bio")
        val bio: String?,
        @SerializedName("ImageUrl")
        val imageUrl: String?,
        @SerializedName("NumberOfViews")
        val numberOfViews: Int?,
        @SerializedName("IsDirectOn")
        val isDirectOn: Boolean?,
        @SerializedName("IsProfilePrivate")
        val isProfilePrivate: Boolean?,
        @SerializedName("DirectType")
        val directType: Int?,
        @SerializedName("FindMeData")
        val findMeData: FindMeData?,
        @SerializedName("PetData")
        val petData: PetData?,
        @SerializedName("MyLinks")
        val myLinks: List<MyLink>?,
        @SerializedName("AllLinks")
        val allLinks: List<AllLink>?
) : Parcelable