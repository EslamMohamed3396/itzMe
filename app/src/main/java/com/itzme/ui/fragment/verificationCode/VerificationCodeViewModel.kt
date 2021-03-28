package com.itzme.ui.fragment.verificationCode

import androidx.lifecycle.LiveData
import com.itzme.data.models.account.verficationCode.response.ResponseConfirmCode
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class VerificationCodeViewModel : BaseViewModel<ResponseConfirmCode>() {
    fun confirmCode(code: String): LiveData<Resource<ResponseConfirmCode>> {
        return callApi(Client.getInstance()?.confirmCode(code)!!)
    }
}