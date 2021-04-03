package com.itzme.ui.fragment.myProfile.viewModels

import androidx.lifecycle.LiveData
import com.itzme.data.models.profile.myProfile.response.ResponseMyProfile
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class MyProfileViewModel : BaseViewModel<ResponseMyProfile>() {
    fun myProfile(): LiveData<Resource<ResponseMyProfile>> {
        return callApi(Client.getInstance()?.myProfile()!!)
    }
}