package com.itzme.ui.fragment.joinNow

import androidx.lifecycle.LiveData
import com.itzme.data.models.registerLoginModel.checkName.ResponseCheckUserName
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class CheckNameViewModel : BaseViewModel<ResponseCheckUserName>() {
    fun checkName(name: String): LiveData<Resource<ResponseCheckUserName>> {
        return callApi(Client.getInstance()?.checkUserName(name)!!)
    }
}