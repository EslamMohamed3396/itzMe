package com.itzme.utilits

import com.google.android.material.textfield.TextInputLayout
import com.itzme.R
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object EditTextValidiation {
    fun validEmail(
        textInputLayout: TextInputLayout
    ): Boolean {
        val validEmail =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val matcherEmail: Matcher =
            Pattern.compile(validEmail)
                .matcher(textInputLayout.editText?.text.toString().toLowerCase(Locale.ROOT))
        return if (matcherEmail.matches()) {
            textInputLayout.error = null
            true
        } else {
            textInputLayout.error =
                textInputLayout.context.resources.getString(R.string.invalidemail)
            textInputLayout.editText?.requestFocus()
            false
        }
    }


    fun validPassword(
        textInputLayout: TextInputLayout
    ): Boolean {
        val pattern: Pattern
        val matcher: Matcher
//            val passwordPattern = "[0-9]{1,6}"
//            val passwordPattern = "((?!\\s)\\A)(\\s|(?<!\\s)\\S){4,20}\\Z"
        val passwordPattern = "^[A-Za-z0-9]{4,20}$"
        pattern = Pattern.compile(passwordPattern)
        matcher = pattern.matcher(textInputLayout.editText?.text.toString())
        return if (matcher.matches()) {
            textInputLayout.error = null
            true
        } else {
            textInputLayout.error =
                textInputLayout.context.resources.getString(R.string.invalidPassword)
            textInputLayout.editText?.requestFocus()
            false
        }
    }

    fun validConfirmPassword(
        Password: TextInputLayout,
        confirmInputLayout: TextInputLayout
    ): Boolean {
        return if (confirmInputLayout.editText?.text.toString() != Password.editText?.text.toString()) {
            confirmInputLayout.error =
                confirmInputLayout.context.getString(R.string.password_match)
            confirmInputLayout.editText?.requestFocus()
            false
        } else {
            confirmInputLayout.error = null
            true
        }
    }


    fun validUserName(
        textInputLayout: TextInputLayout
    ): Boolean {
        val validName = "^(?![ .]*$)[\\p{L} .]*$"

        val mName = textInputLayout.editText?.text.toString().toLowerCase(Locale.ROOT)
        val matcherName: Matcher = Pattern.compile(validName).matcher(mName)
        return if (matcherName.matches() && textInputLayout.editText?.text.toString()
                .trim().isNotEmpty()
        ) {
            textInputLayout.error = null
            true
        } else {
            textInputLayout.error =
                textInputLayout.context.resources.getString(R.string.invalid_name)
            textInputLayout.editText?.requestFocus()
            false
        }
    }
//
//    fun validName(
//        textInputLayout: TextInputLayout
//    ): Boolean {
////            val validName = "^(?![ .]*$)[\\p{L} .]*$"
//
////            val validName = "([a-zA-Z]{2,30})"
//        val validName = "[A-Za-z_]{2,30}"
//
//        val mName = textInputLayout.editText?.text.toString().toLowerCase(Locale.ROOT)
//        val matcherName: Matcher = Pattern.compile(validName).matcher(mName)
//        return if (matcherName.matches()) {
//            textInputLayout.error = null
//            true
//        } else {
//            textInputLayout.error =
//                textInputLayout.context.resources.getString(R.string.invalidName)
//            textInputLayout.editText?.requestFocus()
//            false
//        }
//    }
//
//    fun validField(
//        textInputLayout: TextInputLayout
//    ): Boolean {
////            val validName = "^(?![ .]*$)[\\p{L} .]*$"
//
////            val validName = "([a-zA-Z]{2,30})"
//        //  val validName = "^(?![ .]*$)[\\p{L} .]*$"
//
//        val mName = textInputLayout.editText?.text.toString().toLowerCase(Locale.ROOT)
////        val matcherName: Matcher = Pattern.compile(validName).matcher(mName)
//        return if (mName.length > 3) {
//            textInputLayout.error = null
//            true
//        } else {
//            textInputLayout.error =
//                textInputLayout.context.resources.getString(R.string.invalid)
//            textInputLayout.editText?.requestFocus()
//            false
//        }
//    }
//
//    fun validPhone(
//        textInputLayout: TextInputLayout
//    ): Boolean {
//        val passwordPattern = "([0-9]{11})"
//        val mName = textInputLayout.editText?.text.toString()
//        val matcherName: Matcher = Pattern.compile(passwordPattern).matcher(mName)
//        return if (matcherName.matches()) {
//            textInputLayout.error = null
//            true
//        } else {
//            textInputLayout.error =
//                textInputLayout.context.resources.getString(R.string.invalidPhone)
//            textInputLayout.editText?.requestFocus()
//            false
//        }
//    }
//
//    fun validCode(
//        textInputLayout: TextInputLayout
//    ): Boolean {
//        val passwordPattern = "([0-9]{1})"
//        val mName = textInputLayout.editText?.text.toString()
//        val matcherName: Matcher = Pattern.compile(passwordPattern).matcher(mName)
//        return if (matcherName.matches()) {
//            textInputLayout.error = null
//            true
//        } else {
//            textInputLayout.error =
//                textInputLayout.context.resources.getString(R.string.invalidCode)
//            textInputLayout.editText?.requestFocus()
//            false
//        }
//    }
//
//    fun validAddress(
//        textInputLayout: TextInputLayout
//    ): Boolean {
////            val validName = "^(?![ .]*$)[\\p{L} .]*$"
//
////            val validName = "([a-zA-Z]{2,30})"
//        val validName = "[A-Za-z0-9_]{1,30}"
//
//        val mName = textInputLayout.editText?.text.toString().toLowerCase(Locale.ROOT)
//        val matcherName: Matcher = Pattern.compile(validName).matcher(mName)
//        return if (matcherName.matches()) {
//            textInputLayout.error = null
//            true
//        } else {
//            textInputLayout.error =
//                textInputLayout.context.resources.getString(R.string.invalidAddress)
//            textInputLayout.editText?.requestFocus()
//            false
//        }
//    }
//
//    fun validDropDown(
//        textInputLayout: TextInputLayout
//    ): Boolean {
//        //val validName = "^(?![ .]*$)[\\p{L} .]*$"
//
//        val mName = textInputLayout.editText?.text.toString().toLowerCase(Locale.ROOT)
////        val matcherName: Matcher = Pattern.compile(validName).matcher(mName)
//        return if (mName.length > 1) {
//            textInputLayout.error = null
//            true
//        } else {
//            textInputLayout.error =
//                textInputLayout.context.resources.getString(R.string.invalidDropDown)
//            textInputLayout.editText?.requestFocus()
//            false
//        }
//    }
//
//    fun validNationalId(
//        textInputLayout: TextInputLayout
//    ): Boolean {
//        val passwordPattern = "([0-9]{14})"
//        val mName = textInputLayout.editText?.text.toString()
//        val matcherName: Matcher = Pattern.compile(passwordPattern).matcher(mName)
//        return if (matcherName.matches()) {
//            textInputLayout.error = null
//            true
//        } else {
//            textInputLayout.error =
//                textInputLayout.context.resources.getString(R.string.inValidNationalId)
//            textInputLayout.editText?.requestFocus()
//            false
//        }
//    }
//
//    fun validBirthDate(
//        textInputLayout: TextInputLayout
//    ): Boolean {
//        val mName = textInputLayout.editText?.text.toString()
//        return if (mName.length > 2) {
//            textInputLayout.error = null
//            true
//        } else {
//            textInputLayout.error =
//                textInputLayout.context.resources.getString(R.string.inValidBirthDate)
//            textInputLayout.editText?.requestFocus()
//            false
//        }
//    }


}