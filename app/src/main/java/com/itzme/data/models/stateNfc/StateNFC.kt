package com.itzme.data.models.stateNfc

import android.annotation.SuppressLint
import android.os.Parcelable
import com.itzme.data.models.stateNfc.enmReadWriteItzME.ReadWriteNFC
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class StateNFC(val notify: Boolean, val readWrite: ReadWriteNFC) : Parcelable