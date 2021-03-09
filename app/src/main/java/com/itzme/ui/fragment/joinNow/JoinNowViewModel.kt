package com.itzme.ui.fragment.joinNow

import androidx.lifecycle.LiveData
import com.itzme.data.models.registerationAndLogin.request.BodyRegister
import com.itzme.data.models.registerationAndLogin.response.ResponseRegisterAndLogin
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class JoinNowViewModel : BaseViewModel<ResponseRegisterAndLogin>() {
    fun registration(bodyRegister: BodyRegister): LiveData<Resource<ResponseRegisterAndLogin>> {
        return callApi(Client.getInstance()?.register(bodyRegister)!!)
    }
}