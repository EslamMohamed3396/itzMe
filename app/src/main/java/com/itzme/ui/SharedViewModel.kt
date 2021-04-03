package com.itzme.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _dismissed = MutableLiveData<Boolean>()
    val dismissed: LiveData<Boolean> get() = _dismissed

    fun saveDismissed(dismissed: Boolean) {
        _dismissed.value = dismissed
    }

}