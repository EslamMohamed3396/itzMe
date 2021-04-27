package com.itzme.utilits

import com.google.android.material.textfield.TextInputLayout
import com.hbb20.CountryCodePicker

object CheckValidData {

    fun checkEmail(inputLayout: TextInputLayout): Boolean {
        return EditTextValidiation.validEmail(inputLayout)
    }

    fun checkUrl(inputLayout: TextInputLayout): Boolean {
        return EditTextValidiation.isValidUrl(inputLayout)
    }

    fun checkPassword(inputLayout: TextInputLayout): Boolean {
        return EditTextValidiation.validPassword(inputLayout)
    }

    fun checkConfirmPassword(
            inputLayout: TextInputLayout,
            inputLayoutConfirm: TextInputLayout
    ): Boolean {
        return EditTextValidiation.validConfirmPassword(inputLayout, inputLayoutConfirm)
    }

    fun checkUserName(inputLayout: TextInputLayout): Boolean {
        return EditTextValidiation.validUserName(inputLayout)
    }

    fun checkName(inputLayout: TextInputLayout): Boolean {
        return EditTextValidiation.validUserName(inputLayout)
    }

    fun checkPhone(mPhone: CountryCodePicker, inputLayout: TextInputLayout): Boolean {
        return EditTextValidiation.validPhone(mPhone, inputLayout)
    }
}