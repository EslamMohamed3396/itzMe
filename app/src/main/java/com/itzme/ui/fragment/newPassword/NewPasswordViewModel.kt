package com.itzme.ui.fragment.newPassword

import androidx.lifecycle.LiveData
import com.itzme.data.models.registerLoginModel.registerationAndLogin.response.ResponseRegisterAndLogin
import com.itzme.data.models.registerLoginModel.resetPassword.request.BodyResetPassword
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class NewPasswordViewModel : BaseViewModel<ResponseRegisterAndLogin>() {
    fun resetPassword(bodyResetPassword: BodyResetPassword): LiveData<Resource<ResponseRegisterAndLogin>> {
        return callApi(Client.getInstance()?.resetPassword(bodyResetPassword)!!)
    }
}