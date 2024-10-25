package com.jhavidit.movielisting

import com.google.gson.Gson
import retrofit2.Response


suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                NetworkResult.Success(it)
            } ?: NetworkResult.Error("Unknown Error")
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = parseErrorMessage(errorBody)
            NetworkResult.Error(errorMessage, errorBody)
        }
    } catch (e: Throwable) {
        NetworkResult.Error("Unknown Error", e.message)
    }
}

private fun parseErrorMessage(errorBody: String?): String {
    return try {
        errorBody?.let {
            val errorResponse = Gson().fromJson(it, BaseErrorResponse::class.java)
            errorResponse.message ?: "Unknown Error"
        } ?: "Unknown Error"
    } catch (e: Exception) {
        "Unknown Error"
    }
}