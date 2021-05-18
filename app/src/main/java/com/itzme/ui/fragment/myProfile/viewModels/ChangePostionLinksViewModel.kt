package com.itzme.ui.fragment.myProfile.viewModels

import androidx.lifecycle.LiveData
import com.itzme.data.models.profile.changeLinkPostions.response.ResponseChangeLinkPostions
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class ChangePostionLinksViewModel : BaseViewModel<ResponseChangeLinkPostions>() {
    fun changePostion(type: Int,
                      newPosition: Int,
                      replacedType: Int,
                      oldPosition: Int): LiveData<Resource<ResponseChangeLinkPostions>> {
        return callApi(Client.getInstance()?.changePostion(type, newPosition, replacedType, oldPosition)!!)
    }
}