package com.itzme.ui.fragment.setNewPassword

import androidx.lifecycle.LiveData
import com.itzme.data.models.account.changePassword.request.BodyChangePassword
import com.itzme.data.models.account.changePassword.response.ResponseChangePassword
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class SetNewPasswordViewModel : BaseViewModel<ResponseChangePassword>() {
    fun changePassword(bodyChangePassword: BodyChangePassword): LiveData<Resource<ResponseChangePassword>> {
        return callApi(Client.getInstance()?.changePassword(bodyChangePassword)!!)
    }
}