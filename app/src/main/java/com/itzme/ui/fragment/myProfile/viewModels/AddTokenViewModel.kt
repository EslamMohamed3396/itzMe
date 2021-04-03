package com.itzme.ui.fragment.myProfile.viewModels

import androidx.lifecycle.LiveData
import com.itzme.data.models.notification.request.BodyAddToken
import com.itzme.data.models.notification.response.ResponsePutToken
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class AddTokenViewModel : BaseViewModel<ResponsePutToken>() {
    fun addToken(bodyAddToken: BodyAddToken): LiveData<Resource<ResponsePutToken>> {
        return callApi(Client.getInstance()?.addToken(bodyAddToken)!!)
    }
}