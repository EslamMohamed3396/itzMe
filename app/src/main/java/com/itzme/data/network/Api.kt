package com.itzme.data.network

import com.itzme.data.models.registerLoginModel.checkName.ResponseCheckUserName
import com.itzme.data.models.registerLoginModel.forgetPassword.response.ResponseForgetPassword
import com.itzme.data.models.registerLoginModel.registerationAndLogin.request.BodyLogin
import com.itzme.data.models.registerLoginModel.registerationAndLogin.request.BodyRegister
import com.itzme.data.models.registerLoginModel.registerationAndLogin.response.ResponseRegisterAndLogin
import com.itzme.data.models.registerLoginModel.resetPassword.request.BodyResetPassword
import com.itzme.data.models.registerLoginModel.verficationCode.response.ResponseConfirmCode
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

    //endregion

}