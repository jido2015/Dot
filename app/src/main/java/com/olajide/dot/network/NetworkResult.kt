package com.olajide.dot.network

/**Sealed class for Handling Api Response*/
sealed class NetworkResult<T>(
    val data: T? = null,
    val errorText: String? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(data)
    class Failure<T>(message: String) : NetworkResult<T>(null, message)
    class Loading<T> : NetworkResult<T>()
    class Exception<T>(message: String?) : NetworkResult<T>(null, message)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[Data=$data]"
            is Failure -> "Error[Error=$errorText]"
            is Exception -> "Empty--$errorText"
            is Loading -> "Loading----"
        }
    }
}