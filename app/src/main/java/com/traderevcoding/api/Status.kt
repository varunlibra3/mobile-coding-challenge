package com.traderevcoding.api

import com.traderevcoding.R

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

enum class ErrorType {
    STRING,
    ID
}

data class Error(
    val type: ErrorType,
    val message: String? = null,
    val messageId: Int = R.string.error_occurred
)