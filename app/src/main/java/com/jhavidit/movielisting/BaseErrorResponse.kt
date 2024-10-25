package com.jhavidit.movielisting

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse<T>(
    @SerializedName("status_message")
    val message: String? = null,
    val data: T? = null
)