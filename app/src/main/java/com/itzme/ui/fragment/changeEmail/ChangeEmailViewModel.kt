package com.itzme.ui.fragment.changeEmail

import androidx.lifecycle.LiveData
import com.itzme.data.models.account.changeEmail.response.ResponseChangeEmail
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class ChangeEmailViewModel : BaseViewModel<ResponseChangeEmail>() {
    fun changeEmail(email: String): LiveData<Resource<ResponseChangeEmail>> {
        return callApi(Client.getInstance()?.changeEmail(email)!!)
    }
}