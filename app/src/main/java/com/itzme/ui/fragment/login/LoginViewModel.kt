package com.itzme.ui.fragment.login

import androidx.lifecycle.LiveData
import com.itzme.data.models.account.registerationAndLogin.request.BodyLogin
import com.itzme.data.models.account.registerationAndLogin.response.ResponseRegisterAndLogin
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class LoginViewModel : BaseViewModel<ResponseRegisterAndLogin>() {
    fun login(bodyLogin: BodyLogin): LiveData<Resource<ResponseRegisterAndLogin>> {
        return callApi(Client.getInstance()?.login(bodyLogin)!!)
    }
}