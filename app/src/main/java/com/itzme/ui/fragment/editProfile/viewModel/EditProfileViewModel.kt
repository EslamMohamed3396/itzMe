package com.itzme.ui.fragment.editProfile.viewModel

import androidx.lifecycle.LiveData
import com.itzme.data.models.profile.editProfile.request.BodyEditProfile
import com.itzme.data.models.profile.editProfile.response.ResponseEditProfile
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class EditProfileViewModel : BaseViewModel<ResponseEditProfile>() {

    fun updateProfile(bodyEditProfile: BodyEditProfile): LiveData<Resource<ResponseEditProfile>> {
        return callApi(Client.getInstance()?.updateProfile(bodyEditProfile)!!)
    }
}