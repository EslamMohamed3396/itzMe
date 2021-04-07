package com.itzme.ui.fragment.contactProfile

import androidx.lifecycle.LiveData
import com.itzme.data.models.contact.contactProfile.response.ResponseContactProfile
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class ContactProfileViewModel : BaseViewModel<ResponseContactProfile>() {
    fun contactProfile(contactId: Int): LiveData<Resource<ResponseContactProfile>> {
        return callApi(Client.getInstance()?.contactProfile(contactId)!!)
    }
}