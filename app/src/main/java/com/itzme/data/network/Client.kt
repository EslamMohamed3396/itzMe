package com.itzme.data.network

import com.google.gson.Gson
import com.itzme.data.models.baseResponse.ErrorResponse
import com.itzme.data.models.contact.response.ResponseMyContact
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
import com.itzme.data.models.profile.myProfile.response.ResponseMyProfile
import com.itzme.utilits.App
import com.itzme.utilits.Constant
import com.itzme.utilits.PreferencesUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Client {

    private var sBuilder: OkHttpClient.Builder? = null
    private var apiService: Api? = null
    private var SINSTANCE: Client? = null
    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
        return try {
            if (sBuilder == null) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                sBuilder = OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .addInterceptor(httpLoggingInterceptor)
                sBuilder!!.addInterceptor(Interceptor { chain: Interceptor.Chain ->
                    val original: Request = chain.request()
                    val request: Request = original.newBuilder()
                            .header(
                                    Constant.AUTHORIZATION,
                                    Constant.BEARER + PreferencesUtils(App.getContext()).getInstance()
                                            ?.getUserData(
                                                    Constant.USER_DATA_KEY
                                            )?.token
                            )
                            .build()
                    chain.proceed(request)
                })

            }
            sBuilder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(getUnsafeOkHttpClient()?.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        apiService = retrofit.create(Api::class.java)
    }

    fun getInstance(): Client? {
        if (SINSTANCE == null) {
            SINSTANCE = Client
        }
        return SINSTANCE
    }


    fun <T> request(api: Observable<T>, callBackNetwork: ICallBackNetwork<T>) {
        api.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<T> {
                    override fun onSubscribe(d: Disposable) {
                        callBackNetwork.onDisposable(d)
                    }

                    override fun onNext(u: T) {
                        callBackNetwork.onSuccess(u)
                    }

                    override fun onError(e: Throwable) {

                        if (e is HttpException) {
                            val errorBody: String = e.response()?.errorBody()?.string()!!
                            val errorResponse: ErrorResponse =
                                    Gson().fromJson(errorBody, ErrorResponse::class.java)
                            callBackNetwork.onFailed(
                                    e.message(),
                                    errorResponse.errorCode ?: e.code(),
                                    errorResponse.errorMessage
                            )
                        }
                    }

                    override fun onComplete() {}
                })
    }


    fun register(bodyRegister: BodyRegister): Observable<ResponseRegisterAndLogin> {
        return apiService?.REGISTER(bodyRegister)!!
    }

    fun login(bodyLogin: BodyLogin): Observable<ResponseRegisterAndLogin> {
        return apiService?.LOGIN(bodyLogin)!!
    }

    fun checkUserName(name: String): Observable<ResponseCheckUserName> {
        return apiService?.CHECK_USER_NAME(name)!!
    }

    fun forgotPassword(email: String): Observable<ResponseForgetPassword> {
        return apiService?.FORGET_PASSWORD(email)!!
    }

    fun confirmCode(code: String): Observable<ResponseConfirmCode> {
        return apiService?.CONFIRM_CODE(code)!!
    }

    fun resetPassword(bodyResetPassword: BodyResetPassword): Observable<ResponseRegisterAndLogin> {
        return apiService?.RESET_PASSWORD(bodyResetPassword)!!
    }

    fun changePassword(bodyChangePassword: BodyChangePassword): Observable<ResponseChangePassword> {
        return apiService?.CHANGE_PASSWORD(bodyChangePassword)!!
    }

    fun changeEmail(email: String): Observable<ResponseChangeEmail> {
        return apiService?.CHANGE_EMAIL(email)!!
    }

    fun resendChangeEmail(): Observable<ResponseChangeEmail> {
        return apiService?.RESEND_CHANGE_EMAIL()!!
    }

    fun confirmChangeEmail(code: String): Observable<ResponseRegisterAndLogin> {
        return apiService?.CONFIRM_CHANGE_EMAIL(code)!!
    }

    fun myContact(): Observable<ResponseMyContact> {
        return apiService?.MY_CONTACT()!!
    }

    fun myProfile(): Observable<ResponseMyProfile> {
        return apiService?.MY_PROFILE()!!
    }

}
