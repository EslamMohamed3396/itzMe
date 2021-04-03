package com.itzme.ui.fragment.editProfile.viewModel

import androidx.lifecycle.LiveData
import com.itzme.data.models.profile.editLink.request.BodyEditLink
import com.itzme.data.models.profile.editProfile.response.ResponseEditProfile
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class EditLinkViewModel : BaseViewModel<ResponseEditProfile>() {

    fun updateLink(bodyEditLink: BodyEditLink): LiveData<Resource<ResponseEditProfile>> {
        return callApi(Client.getInstance()?.updateLink(bodyEditLink)!!)
    }
}