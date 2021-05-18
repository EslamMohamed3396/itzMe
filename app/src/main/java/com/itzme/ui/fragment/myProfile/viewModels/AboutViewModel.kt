package com.itzme.ui.fragment.myProfile.viewModels

import androidx.lifecycle.LiveData
import com.itzme.data.models.metaData.response.ResponseMetaData
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class AboutViewModel : BaseViewModel<ResponseMetaData>() {
    fun about(): LiveData<Resource<ResponseMetaData>> {
        return callApi(Client.getInstance()?.about()!!)
    }
}