package com.itzme.data.models.profile.myProfile.response


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class AllLink(
    @SerializedName("Name")
    val name: String?,
    @SerializedName("Links")
    val links: List<Link>?
) : Parcelable