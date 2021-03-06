package com.dayrah.network

interface Api {
//    @POST("user/register")
//    fun REGISTER_REQUEST(@Body registerModel: RegisterModel): Observable<ResponseRegister>
//
//    @POST("user/login")
//    fun LOGIN_REQUEST(@Body loginModel: LoginModel): Observable<ResponseLogin>
//
//    @POST("user/ForgotPassword")
//    fun FORGET_REQUEST(@Body forgetModel: ForgetModel): Observable<ResponseForget>
//
//    @POST("user/confirmCode")
//    fun CONFIRM_REQUEST(@Body confirmModel: ConfirmModel): Observable<ResponseConfirm>
//
//    @POST("user/AddNewPassword")
//    fun NEW_PASSWORD_REQUEST(@Body newPasswordModel: NewPasswordModel): Observable<ResponseNewPassword>
//
////    @GET("Circle/getAllActiveCircles")
////    fun GET_ALL_ACTIVE_CIRCLES_REQUEST(
////        @Query("lang") lang: String,
////        @Query("userId") userId: String? = null
////    ): Observable<ResponseActiveCircle>
//
//    @GET("Circle/GetCircleById")
//    fun GET_CIRCLES_BY_ID_REQUEST(
//        @Query("circleId") circleId: String?,
//        @Query("lang") lang: String
//    ): Observable<ResponseCompanyById>
//
//    @POST("Circle/searchCirclesWithFilters")
//    fun GET_SEARCH_PRODUCT_REQUEST(
//        @Body searchModel: SearchModel,
//        @Query("lang") lang: String
//    ): Observable<ResponseSearch>
//
//    @Multipart
//    @POST("userCircle/requestJoinCircleNew")
//    fun UPDATE_USER_CIRCLE_REQUEST(
//        @Query("lang") lang: String,
//        @Part("circleId") circleId: RequestBody?,
//        @Part("index") index: RequestBody?,
//        @Part("userId") userId: RequestBody?,
//        @Part("city") city: RequestBody?,
//        @Part("address") address: RequestBody?,
//        @Part("district") district: RequestBody?,
//        @Part("defaultPayment") defaultPayment: RequestBody?,
//        @Part("from") from: RequestBody?,
//        @Part("to") to: RequestBody?,
//        @Part frontId: MultipartBody.Part?,
//        @Part backId: MultipartBody.Part?
//    ): Observable<ResponseJoinCircle>
//
//
//    //region route wishlist
//    @POST("WishList/addRemoveWishList")
//    fun ADD_WISH_LIST_REQUEST(
//        @Query("userId") userId: String,
//        @Query("circleId") circleId: String,
//        @Query("lang") lang: String
//    ): Observable<BaseResponse>
//
//    //
////    @GET("WishList/getMyWishList")
////    fun GET_WISH_LIST_REQUEST(
////        @Query("userId") userId: String,
////        @Query("pageNo") pageNumber: Int,
////        @Query("lang") lang: String
////    ): Observable<ResponseActiveCircle>
//    @GET("WishList/getMyWishList")
//    fun GET_WISH_LIST_REQUEST(
//        @Query("userId") userId: String,
//        @Query("lang") lang: String
//    ): Observable<ResponseActiveCircle>
//
//    //endregion
//
//
//    //region notifications
//
//    @GET("account/getNotificationsByUserId")
//    fun GET_NOTIFICATIONS(
//        @Query("userId") userId: String?,
//        @Query("lang") lang: String
//    ): Observable<ResponseNotifications>
//
//    @GET("Circle/getRelatedCircles")
//    fun GET_RELATED_CIRCLES(
//        @Query("userId") userId: String?,
//        @Query("lang") lang: String?,
//        @Query("circleId") circleId: String?
//    ): Observable<ResponseRelatedCircle>
//
//    @GET("CircleProduct/getProductByCircleId")
//    fun GET_CIRCLES_PRODUCT_DETAIL(
//        @Query("userId") userId: String?,
//        @Query("lang") lang: String?,
//        @Query("circleId") circleId: String?
//    ): Observable<ResponseProductDetail>
//
//    //endregion
//
//    //region my circle
//
//    @GET("Circle/getCirclesByUserId")
//    fun GET_MY_ACTIVE_CIRCLE(
//        @Query("userId") userId: String?,
//        @Query("lang") lang: String,
//        @Query("view") active: Int
//    ): Observable<ResponseMyActiveCircleOld>
//
//    @GET("Circle/GetActiveCircleByUserId")
//    fun GET_MY_ACTIVE_CIRCLE(
//        @Query("userId") userId: String?,
//        @Query("lang") langId: String
//    ): Observable<ResponseMyActiveCircle>
//
//    //endregion
//
//    //region account privacy and terms
//
//    @GET("account/getTermsAndConditions")
//    fun GET_TERMS(
//        @Query("lang") lang: String
//    ): Observable<ResponseTerms>
//
//    @GET("account/getPrivacy")
//    fun GET_PRVACY(
//        @Query("lang") lang: String
//    ): Observable<ResponseTerms>
//
//    @GET("Contact/getAllContacts")
//    fun GET_CONTACT(): Observable<ResponseContacts>
//
//    @GET("account/getFAQ")
//    fun GET_FAQ(
//        @Query("lang") lang: String
//    ): Observable<ResponseFaq>
//
//    //endregion
//
//
//    //region circle
//
//
//    @GET("Circle/getAllActiveCircles")
//    fun GET_ALL_CIRCLES_REQUEST(
//        @Query("lang") lang: String,
//        @Query("userId") userId: String? = null,
//        @Query("pageNo") pageNo: Int
//    ): Observable<ResponseAllCircles>
//
//    @GET("user/getUserData")
//    fun GET_USER_DATA_REQUEST(
//        @Query("userId") userId: String?,
//        @Query("lang") lang: String?
//    ): Observable<ResponseUserData>
//
//    @GET("user/getCreditByUserId")
//    fun GET_CREDIT_DATA_REQUEST(
//        @Query("userId") userId: String?,
//        @Query("lang") lang: String?
//    ): Observable<ResponseCreditByUserID>
//
//    @GET("user/checkAddressandCreditExist")
//    fun GET_CREDIT_ADDRESS_ID_EXIST_REQUEST(
//        @Query("userId") userId: String?,
//        @Query("lang") lang: String?
//    ): Observable<ResponseCreditAddressIdExist>
//
//    @GET("Circle/getAllFilters")
//    fun GET_FILTER_REQUEST(
//        @Query("lang") lang: String?
//    ): Observable<ResponseFilter>
//
//    @GET("account/GetVersionCode")
//    fun GET_VERSION_CODE_REQUEST(): Observable<ResponseVersionCode>
//
//    //endregion
//
//    //region payment
//
//    @GET("account/getPaymentTypes")
//    fun GET_PAYMENT_TYPES(
//        @Query("userId") userId: String?,
//        @Query("lang") lang: String? = Constant.LANG_ID
//    ): Observable<ResponsePaymentTypes>
//
//    @POST("user/updateDefaultPayment")
//    fun POST_UPDATE_DEFAULT_PAYMENT(
//        @Query("userId") userId: String?, @Query("paymentId") paymentId: String
//    ): Observable<ResponseUpdateDefaultPayment>
//
//    @GET("account/getworkHours")
//    fun GET_WORK_HOUR(): Observable<ResponseWorkHour>
//
//    @GET("account/getSampleContract")
//    fun GET_CONTRACT(): Observable<ResponseContract>
//
//    //endregion
//
//    //region cities
//
//    @GET("account/getGovernmentList")
//    fun GET_CITIES(@Query("lang") lang: String): Observable<ResponseGetCities>
//
//    //endregion
//
//    //region contactUs
//
//    @POST("Contact/addInquiry")
//    fun CONTACT_US(
//        @Query("lang") lang: String,
//        @Body contactUsBody: ContactUsBody
//    ): Observable<BaseResponse>
//
//
//    @POST("user/payCashRequest")
//    fun POST_PAY_CASH(
//        @Body payCashModel: PayCashModel,
//        @Query("lang") lang: String = Constant.LANG_ID
//    ): Observable<ResponsePayCash>
//
//    @GET("userCircle/checkrRquestJoinCircle")
//    fun POST_CHECK_JOIN_CIRCLE(
//        @Query("lang") lang: String,
//        @Query("circleId") circleId: String,
//        @Query("index") index: Int,
//        @Query("userId") userId: String,
//    ): Observable<BaseResponse>
//

}