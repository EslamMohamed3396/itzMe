package com.itzme.ui.fragment.verificationCode

import androidx.lifecycle.LiveData
import com.itzme.data.models.account.changeEmail.response.ResponseChangeEmail
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class ResendChangeEmailViewModel : BaseViewModel<ResponseChangeEmail>() {
    fun resendChangeEmail(): LiveData<Resource<ResponseChangeEmail>> {
        return callApi(Client.getInstance()?.resendChangeEmail()!!)
    }
}