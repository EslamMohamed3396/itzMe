package com.itzme.data.network

import com.itzme.data.models.checkName.ResponseCheckUserName
import com.itzme.data.models.registerationAndLogin.request.BodyLogin
import com.itzme.data.models.registerationAndLogin.request.BodyRegister
import com.itzme.data.models.registerationAndLogin.response.ResponseRegisterAndLogin
import com.itzme.utilits.Constant
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @POST(Constant.REGISTER)
    fun REGISTER(@Body bodyRegister: BodyRegister): Observable<ResponseRegisterAndLogin>

    @POST(Constant.LOGIN)
    fun LOGIN(@Body bodyLogin: BodyLogin): Observable<ResponseRegisterAndLogin>

    @GET(Constant.CHECK_USER_NAME)
    fun CHECK_USER_NAME(@Query("q") name: String): Observable<ResponseCheckUserName>
}