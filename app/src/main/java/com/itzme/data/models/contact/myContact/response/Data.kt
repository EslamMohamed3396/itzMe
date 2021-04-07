package com.itzme.data.models.contact.myContact.response


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Data(
    @SerializedName("Id")
    val id: Int?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("ImageUrl")
    val imageUrl: String?,
    @SerializedName("NumberOfViews")
    val numberOfViews: Int?
) : Parcelable