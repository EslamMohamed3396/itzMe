package com.itzme.ui.fragment.myProfile.viewModels

import androidx.lifecycle.LiveData
import com.itzme.data.models.account.logOut.response.ResponseLogOut
import com.itzme.data.models.notification.request.BodyAddToken
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class LogOutViewModel : BaseViewModel<ResponseLogOut>() {
    fun logOut(bodyAddToken: BodyAddToken): LiveData<Resource<ResponseLogOut>> {
        return callApi(Client.getInstance()?.logout(bodyAddToken)!!)
    }
}