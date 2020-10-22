package com.traderevcoding.api

import com.traderevcoding.R
import org.json.JSONObject
import retrofit2.HttpException
import java.net.UnknownHostException

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val error: Error?
) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, error = null)

        private fun <T> error(message: String): Resource<T> =
        Resource(status = Status.ERROR, data = null , error = Error(type = ErrorType.STRING, message = message))

        fun <T> error(messageId: Int): Resource<T> =
            Resource(status = Status.ERROR, data = null , error = Error(type = ErrorType.ID, messageId = messageId))

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, error = null)

        fun <T> networkError(exception: Exception): Resource<T> {
            var error: Resource<T> = error(messageId = R.string.error_occurred)
            when (exception) {
                is HttpException -> {
                    exception.response()?.let {
                        it.errorBody()?.let {
                            val jObjError = JSONObject(it.string())
                            if (jObjError.has("message"))
                                error = error(jObjError.getString("message"))
                            else if (jObjError.has("errorMessage"))
                                error = error(jObjError.getString("errorMessage"))
                        }
                    }
                }
                is UnknownHostException -> { error = error(messageId = R.string.internet_not_connected) }
            }
            return error
        }
    }
}