package com.itzme.ui.fragment.myProfile.viewModels

import androidx.lifecycle.LiveData
import com.itzme.data.models.profile.turnOnOffProfile.response.ResponseProfileOnOff
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class TurnOnOffProfileViewModel : BaseViewModel<ResponseProfileOnOff>() {
    fun turnOnOffProfile(): LiveData<Resource<ResponseProfileOnOff>> {
        return callApi(Client.getInstance()?.turnOnOffProfile()!!)
    }
}