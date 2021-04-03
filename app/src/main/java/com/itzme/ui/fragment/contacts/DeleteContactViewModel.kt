package com.itzme.ui.fragment.contacts

import androidx.lifecycle.LiveData
import com.itzme.data.models.contact.removeContact.response.ResponseRemoveContact
import com.itzme.data.network.Client
import com.itzme.ui.base.BaseViewModel
import com.itzme.utilits.Resource

class DeleteContactViewModel : BaseViewModel<ResponseRemoveContact>() {

    fun deleteContact(contactId: Int): LiveData<Resource<ResponseRemoveContact>> {
        return callApi(Client.getInstance()?.deleteContact(contactId)!!)
    }
}