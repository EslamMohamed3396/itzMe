package com.itzme.data.network

import com.itzme.data.models.account.changeEmail.response.ResponseChangeEmail
import com.itzme.data.models.account.changePassword.request.BodyChangePassword
import com.itzme.data.models.account.changePassword.response.ResponseChangePassword
import com.itzme.data.models.account.checkName.ResponseCheckUserName
import com.itzme.data.models.account.forgetPassword.response.ResponseForgetPassword
import com.itzme.data.models.account.registerationAndLogin.request.BodyLogin
import com.itzme.data.models.account.registerationAndLogin.request.BodyRegister
import com.itzme.data.models.account.registerationAndLogin.response.ResponseRegisterAndLogin
import com.itzme.data.models.account.resetPassword.request.BodyResetPassword
import com.itzme.data.models.account.verficationCode.response.ResponseConfirmCode
import com.itzme.data.models.contact.response.ResponseMyContact
import com.itzme.data.models.profile.myProfile.response.ResponseMyProfile
import com.itzme.utilits.Constant
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    //endregion

    //region contact
    @GET(Constant.MY_CONTACT)
    fun MY_CONTACT(@Query("lang") lang: String = Constant.LANG_APP): Observable<ResponseMyContact>


    //endregion
    //region profile

    @GET(Constant.MY_PROFILE)
    fun MY_PROFILE(@Query("lang") lang: String = Constant.LANG_APP): Observable<ResponseMyProfile>


    //endregion

}