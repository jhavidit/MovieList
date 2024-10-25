package com.jhavidit.movielisting

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String, val errorBody: String? = null) :
        NetworkResult<Nothing>()
}