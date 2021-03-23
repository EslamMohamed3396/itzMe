package com.itzme.utilits

import com.google.android.material.textfield.TextInputLayout

object CheckValidData {

    fun checkEmail(inputLayout: TextInputLayout): Boolean {
        return EditTextValidiation.validEmail(inputLayout)
    }

    fun checkPassword(inputLayout: TextInputLayout): Boolean {
        return EditTextValidiation.validPassword(inputLayout)
    }

    fun checkConfirmPassword(inputLayout: TextInputLayout, inputLayoutConfirm: TextInputLayout): Boolean {
        return EditTextValidiation.validConfirmPassword(inputLayout, inputLayoutConfirm)
    }

    fun checkName(inputLayout: TextInputLayout): Boolean {
        return EditTextValidiation.validUserName(inputLayout)
    }
}