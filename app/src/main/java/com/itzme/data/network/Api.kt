package com.itzme.data.network

import com.itzme.data.models.account.changeEmail.response.ResponseChangeEmail
import com.itzme.data.models.account.changePassword.request.BodyChangePassword
import com.itzme.data.models.account.changePassword.response.ResponseChangePassword
import com.itzme.data.models.account.checkName.ResponseCheckUserName
import com.itzme.data.models.account.forgetPassword.response.ResponseForgetPassword
import com.itzme.data.models.account.logOut.response.ResponseLogOut
import com.itzme.data.models.account.registerationAndLogin.request.BodyLogin
import com.itzme.data.models.account.registerationAndLogin.request.BodyRegister
import com.itzme.data.models.account.registerationAndLogin.response.ResponseRegisterAndLogin
import com.itzme.data.models.account.resetPassword.request.BodyResetPassword
import com.itzme.data.models.account.verficationCode.response.ResponseConfirmCode
import com.itzme.data.models.contact.contactProfile.response.ResponseContactProfile
import com.itzme.data.models.contact.myContact.response.ResponseMyContact
import com.itzme.data.models.contact.removeContact.response.ResponseRemoveContact
import com.itzme.data.models.metaData.response.ResponseMetaData
import com.itzme.data.models.notification.request.BodyAddToken
import com.itzme.data.models.notification.response.ResponsePutToken
import com.itzme.data.models.profile.changeLinkPostions.response.ResponseChangeLinkPostions
import com.itzme.data.models.profile.directOnOff.response.ResponseDirectOnOff
import com.itzme.data.models.profile.editLink.request.BodyEditLink
import com.itzme.data.models.profile.editProfile.request.BodyEditProfile
import com.itzme.data.models.profile.editProfile.response.ResponseEditProfile
import com.itzme.data.models.profile.myProfile.response.ResponseMyProfile
import com.itzme.data.models.profile.turnOnOffProfile.response.ResponseProfileOnOff
import com.itzme.data.models.tags.tagType.response.ResponseTagType
import com.itzme.data.models.tags.validateTage.response.ResponseValidateTag
import com.itzme.utilits.Constant
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    //region account
    @POST(Constant.REGISTER)
    fun REGISTER(@Body bodyRegister: BodyRegister): Observable<ResponseRegisterAndLogin>

    @POST(Constant.LOGIN)
    fun LOGIN(@Body bodyLogin: BodyLogin): Observable<ResponseRegisterAndLogin>

    @GET(Constant.CHECK_USER_NAME)
    fun CHECK_USER_NAME(@Query("q") name: String): Observable<ResponseCheckUserName>

    @GET(Constant.FORGET_PASSWORD)
    fun FORGET_PASSWORD(@Query("email") name: String): Observable<ResponseForgetPassword>


    @GET(Constant.CONFIRM_CODE)
    fun CONFIRM_CODE(@Query("Code") code: String): Observable<ResponseConfirmCode>


    @POST(Constant.RESET_PASSWORD)
    fun RESET_PASSWORD(@Body bodyResetPassword: BodyResetPassword): Observable<ResponseRegisterAndLogin>


    @POST(Constant.CHANGE_PASSWORD)
    fun CHANGE_PASSWORD(@Body bodyChangePassword: BodyChangePassword): Observable<ResponseChangePassword>


    @GET(Constant.CHANGE_EMAIL)
    fun CHANGE_EMAIL(@Query("Email") email: String): Observable<ResponseChangeEmail>


    @GET(Constant.RESEND_CHANGE_EMAIL)
    fun RESEND_CHANGE_EMAIL(): Observable<ResponseChangeEmail>


    @GET(Constant.CONFIRM_CHANGE_EMAIL)
    fun CONFIRM_CHANGE_EMAIL(@Query("Code") Code: String): Observable<ResponseRegisterAndLogin>

    @POST(Constant.LOG_OUT)
    fun LOG_OUT(@Body bodyAddToken: BodyAddToken): Observable<ResponseLogOut>

    //endregion

    //region contact
    @GET(Constant.MY_CONTACT)
    fun MY_CONTACT(@Query("lang") lang: String = Constant.LANG_NAME): Observable<ResponseMyContact>


    @DELETE(Constant.DELETE_CONTACT)
    fun DELETE_CONTACT(@Query("ContactId") contactId: Int): Observable<ResponseRemoveContact>


    //endregion

    //region Token
    @POST(Constant.ADD_TOKEN)
    fun ADD_TOKEN(@Body bodyAddToken: BodyAddToken): Observable<ResponsePutToken>


    //endregion

    //region Tags
    @GET(Constant.TAG_TYPE)
    fun TAG_TYPE(@Query("lang") lang: String = Constant.LANG_NAME): Observable<ResponseTagType>

    @GET(Constant.VALIDATE_TAGE)
    fun VALIDATE_TAGE(@Query("Serial") seria: String): Observable<ResponseValidateTag>

    @GET(Constant.READ_TAGE)
    fun READ_TAGE(
            @Query("Username") username: String?,
            @Query("Serial") serial: String?
    ): Observable<ResponseValidateTag>


    //endregion

    //region profile

    @GET(Constant.MY_PROFILE)
    fun MY_PROFILE(@Query("lang") lang: String = Constant.LANG_NAME): Observable<ResponseMyProfile>


    @GET(Constant.DIRECT_ON_OFF)
    fun DIRECT_ON_OFF(
            @Query("IsToggleStatus") isToggleStatus: Boolean,
            @Query("type") type: Int
    ): Observable<ResponseDirectOnOff>

    @PUT(Constant.UPDATE_PROFILE)
    fun UPDATE_PROFILE(@Body bodyEditProfile: BodyEditProfile): Observable<ResponseEditProfile>

    @POST(Constant.UPDATE_LINK)
    fun UPDATE_LINK(@Body bodyEditLink: BodyEditLink): Observable<ResponseEditProfile>


    @GET(Constant.TURN_ON_OFF_PROFILE)
    fun TURN_ON_OFF_PROFILE(): Observable<ResponseProfileOnOff>

    @GET(Constant.CONTACT_PROFILE)
    fun CONTACT_PROFILE(
            @Query("ContactId") contactId: Int,
            @Query("lang") lang: String = Constant.LANG_NAME
    ): Observable<ResponseContactProfile>


    @GET(Constant.CHANGE_POSTION)
    fun CHANGE_POSTION(
            @Query("type") type: Int,
            @Query("newPosition") newPosition: Int,
            @Query("replacedType") replacedType: Int,
            @Query("oldPosition") oldPosition: Int
    ): Observable<ResponseChangeLinkPostions>


    //endregion


    //region about

    @GET(Constant.ABOUT)
    fun ABOUT(): Observable<ResponseMetaData>


    //endregion

}