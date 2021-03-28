package com.itzme.ui.fragment.forgetPasword

import androidx.lifecycle.LiveData
import com.itzme.data.models.account.forgetPassword.response.ResponseForgetPassword
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class ForgetPasswordViewModel : BaseViewModel<ResponseForgetPassword>() {

    fun forgotPassword(email: String): LiveData<Resource<ResponseForgetPassword>> {
        return callApi(Client.getInstance()?.forgotPassword(email)!!)
    }
}