package com.itzme.utilits

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}

//sealed class Resource<T> {
//    class Success<T>(val data: T) : Resource<T>()
//    class Failed<T>(val message: String) : Resource<T>()
//}
