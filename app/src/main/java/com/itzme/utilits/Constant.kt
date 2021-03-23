package com.itzme.utilits

import android.Manifest

object Constant {

    //region base url
    const val BASE_URL = "http://itzme.somee.com/api/"
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Bearer "

    private const val ROUTE_ACCOUNT = "account/"
    const val REGISTER = ROUTE_ACCOUNT + "register"
    const val LOGIN = ROUTE_ACCOUNT + "login"
    const val CHECK_USER_NAME = ROUTE_ACCOUNT + "CheckUsernameAvailability"
    const val FORGET_PASSWORD = ROUTE_ACCOUNT + "ForgotPassword"
    const val CONFIRM_CODE = ROUTE_ACCOUNT + "ValidateForgotPasswordCode"
    const val RESET_PASSWORD = ROUTE_ACCOUNT + "ResetPassword"


    //endregion


    //shared Preference
    const val PRIVATE_MODE = 0
    const val sharedPrefFile = "itzmeSharedPreference"

    //shared Preference Key
    const val USER_ID_KEY = "user_id_key"
    const val USER_NAME_KEY = "user_name_key"
    const val PHONE_KEY = "phone_key"
    const val FIRST_NAME_KEY = "first_name_key"
    const val LAST_NAME_KEY = "last_name_key"
    const val EMAIL_KEY = "email_key"
    const val IS_ACTIVE_KEY = "is_active_key"
    const val CODE_KEY = "code_key"
    const val IS_FIRST_TIME_KEY = "is_first_time"
    const val LANGUAGE_KEY = "language_key"
    const val IS_USER_LOGIN = "login_key"
    const val AUTH_KEY = "auth_key"
    const val USER_DATA_KEY = "user_data_key"
    const val LANG_APP = "lang"

    //navigation args
    const val CIRCLE_ID_ARGS = "circle_Id_args"
    const val MAX_VALUE_ARGS = "max_value_args"
    const val MIN_VALUE_ARGS = "min_value_args"
    const val IMAGE_ARGS = "image_args"
    const val FROM_WISH_LIST_ARGS = "wish_list_args"
    const val QUESTION_ARGS = "question_args"
    const val ANSWER_ARGS = "answer_args"
    const val CATEGORY_ARGS = "category_args"
    const val MONTH_ARGS = "month_args"


    //Language key
    const val AR_LANG_CODE = "5ff2e4109554686cb4354bfc"
    const val EN_LANG_CODE = "5ff2e4069554686cb4354bfb"
    var LANG_NAME = ""

    //Language
    const val ARABIC_LANGUAGE = "ar"
    const val ENGLISH_LANGUAGE = "en"


    //GENDER key
    const val MALE_GENDER = "600553657c7fe047a0e3cd50"
    const val FEMALE_GENDER = "600553727c7fe047a0e3cd51"

    //Slots View Type
    const val SLOTS_AVAILABLE = 0
    const val SLOTS_FULL = 1

    //notification View Type
    const val IS_Read_NOTIFICATION = 0
    const val IS_NOT_Read_NOTIFICATION = 1

    //Manifest Permission
    const val cameraPermission = Manifest.permission.CAMERA
    const val storagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

    // my circle tab number
    const val ACTIVE_NUMBER = 0
    const val PAST_NUMBER = 1
    const val PENDING_NUMBER = 2

}