package com.itzme.ui.fragment.contacts

import androidx.lifecycle.LiveData
import com.itzme.data.models.contact.myContact.response.ResponseMyContact
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class ContactViewModel : BaseViewModel<ResponseMyContact>() {

    fun myContact(): LiveData<Resource<ResponseMyContact>> {
        return callApi(Client.getInstance()?.myContact()!!)
    }
}