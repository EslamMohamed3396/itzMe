package com.itzme.ui.activity.main

import androidx.lifecycle.LiveData
import com.itzme.data.models.tags.validateTage.response.ResponseValidateTag
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class ReadTagViewModel : BaseViewModel<ResponseValidateTag>() {
    fun readTag(userName: String, serial: String): LiveData<Resource<ResponseValidateTag>> {
        return callApi(Client.getInstance()?.readTag(userName, serial)!!)
    }
}