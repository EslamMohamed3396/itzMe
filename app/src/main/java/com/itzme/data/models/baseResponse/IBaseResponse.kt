package com.itzme.data.models.baseResponse


abstract class IBaseResponse {

    abstract val errorCode: Int?
    abstract val errorMessage: String?
}