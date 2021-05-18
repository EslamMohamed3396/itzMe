package com.itzme.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itzme.data.models.stateNfc.StateNFC

class SharedViewModel : ViewModel() {

    private var _dismissed = MutableLiveData<Boolean>()
    val dismissed: LiveData<Boolean> get() = _dismissed

    private var _notifyToMyProfile = MutableLiveData<Boolean>()
    val notifyToMyProfile: LiveData<Boolean> get() = _notifyToMyProfile

    private var _stateNfc = MutableLiveData<StateNFC>()
    val stateNfc: LiveData<StateNFC> get() = _stateNfc

    fun saveDismissed(dismissed: Boolean) {
        _dismissed.value = dismissed
    }

    fun saveNotify(notify: Boolean) {
        _notifyToMyProfile.value = notify
    }

    fun saveState(stateNfc: StateNFC) {
        _stateNfc.value = stateNfc
    }


}
