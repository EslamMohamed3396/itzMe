package com.itzme.ui.fragment.activeList

import androidx.lifecycle.LiveData
import com.itzme.data.models.tags.tagType.response.ResponseTagType
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class ActiveListViewModel : BaseViewModel<ResponseTagType>() {
    fun tagType(): LiveData<Resource<ResponseTagType>> {
        return callApi(Client.getInstance()?.tagType()!!)
    }
}