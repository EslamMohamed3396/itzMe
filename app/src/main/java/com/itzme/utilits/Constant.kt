package com.itzme.utilits

import android.Manifest

object Constant {

    //region base url
    const val BASE_URL = "https://itzmeapp.com/api/"
    const val BASE_URL_IMAGE = "https://itzmeapp.com/"
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Bearer "

    private const val ROUTE_ACCOUNT = "account/"
    const val REGISTER = ROUTE_ACCOUNT + "register"
    const val LOGIN = ROUTE_ACCOUNT + "login"
    const val CHECK_USER_NAME = ROUTE_ACCOUNT + "CheckUsernameAvailability"
    const val FORGET_PASSWORD = ROUTE_ACCOUNT + "ForgotPassword"
    const val CONFIRM_CODE = ROUTE_ACCOUNT + "ValidateForgotPasswordCode"
    const val RESET_PASSWORD = ROUTE_ACCOUNT + "ResetPassword"
    const val CHANGE_PASSWORD = ROUTE_ACCOUNT + "ChangePassword"
    const val CHANGE_EMAIL = ROUTE_ACCOUNT + "RequestChangeEmail"
    const val RESEND_CHANGE_EMAIL = ROUTE_ACCOUNT + "ResendChangeEmailCode"
    const val CONFIRM_CHANGE_EMAIL = ROUTE_ACCOUNT + "ConfirmChangeEmail"
    const val LOG_OUT = ROUTE_ACCOUNT + "logout"


    private const val ROUTE_CONTACT = "contacts/"

    const val MY_CONTACT = ROUTE_CONTACT + "mycontacts"
    const val DELETE_CONTACT = ROUTE_CONTACT + "Delete"

    private const val ROUTE_PROFILE = "profile/"

    const val MY_PROFILE = ROUTE_PROFILE + "get"
    const val DIRECT_ON_OFF = ROUTE_PROFILE + "ToggleDirect"
    const val UPDATE_PROFILE = ROUTE_PROFILE + "Update"
    const val UPDATE_LINK = ROUTE_PROFILE + "UpdateLink"
    const val CONTACT_PROFILE = ROUTE_PROFILE + "Contact"
    const val TURN_ON_OFF_PROFILE = ROUTE_PROFILE + "togglevisibility"

    private const val ROUTE_NOTIFICATIONS = "PushTokens/"

    const val ADD_TOKEN = ROUTE_NOTIFICATIONS + "Add"


    private const val TAGS = "tags/"

    const val TAG_TYPE = TAGS + "gettypes"
    const val VALIDATE_TAGE = TAGS + "ValidateSerial"
    const val READ_TAGE = TAGS + "Read"


    //endregion


    //region link type
    const val LINK_PET = 42
    const val LINK_FIND_ME = 43


    //endregion

    //region facebook link

    const val FACEBOOK_LINK = "https://www.facebook.com/joinitzME"


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
    const val writeStoragePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE
    const val readStoragePermission = Manifest.permission.READ_EXTERNAL_STORAGE
    const val IMAGE_PICK_CODE = 22
    const val CAMERA_CODE = 23
    const val MEDIA_REQUEST_CODE = 24


    // my circle tab number
    const val ACTIVE_NUMBER = 0
    const val PAST_NUMBER = 1
    const val PENDING_NUMBER = 2

}