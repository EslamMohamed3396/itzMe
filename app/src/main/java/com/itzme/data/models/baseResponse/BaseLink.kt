package com.itzme.data.models.baseResponse

import android.os.Parcelable


abstract class BaseLink : Parcelable {
    abstract val linkType: Int?
    abstract val linkTypeName: String?
    abstract val linkPlaceholder: String?
    abstract val linkHint: String?
    abstract val linkValidation: String?
    abstract val linkIconUrl: String?
    abstract val linkPosition: Int?
    abstract val name: String?
    abstract val link: String?
    abstract val imageUrl: String?
    abstract val isHidden: Boolean?
    abstract val isActive: Boolean?
    abstract val address: String?
    abstract val information: String?
    abstract val bloodType: String?
    abstract val emergencyContactName: String?
    abstract val emergencyContactPhone: String?
    abstract val petType: String?
}