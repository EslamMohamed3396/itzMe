package com.itzme.ui.fragment.myProfile.viewModels

import androidx.lifecycle.LiveData
import com.itzme.data.models.profile.directOnOff.response.ResponseDirectOnOff
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class DirectOnOffViewModel : BaseViewModel<ResponseDirectOnOff>() {
    fun directOnOff(isToggleStatus: Boolean,
                    type: Int): LiveData<Resource<ResponseDirectOnOff>> {
        return callApi(Client.getInstance()?.directOnOff(isToggleStatus, type)!!)
    }
}