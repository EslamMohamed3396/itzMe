package com.itzme.ui.fragment.verificationCode

import androidx.lifecycle.LiveData
import com.itzme.data.models.account.registerationAndLogin.response.ResponseRegisterAndLogin
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class ConfirmChangeEmailViewModel : BaseViewModel<ResponseRegisterAndLogin>() {
    fun confirmChangeEmail(code: String): LiveData<Resource<ResponseRegisterAndLogin>> {
        return callApi(Client.getInstance()?.confirmChangeEmail(code)!!)
    }
}