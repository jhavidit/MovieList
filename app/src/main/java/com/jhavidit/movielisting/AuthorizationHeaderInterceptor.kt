package com.jhavidit.movielisting

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import timber.log.Timber
import javax.inject.Inject

class AuthorizationHeaderInterceptor @Inject constructor() :
    Interceptor {
    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()
        val authToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMTY5ODlhMjFjOTRmZjFhYmQ0NTVkNDUwNGMyNDIwYiIsIm5iZiI6MTcyOTcwNzk3NS4zNTE5MzMsInN1YiI6IjVlN2RmZTMzZjc5NGFkMDAxM2YwNzllMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Hfefojmbx11daBkFfuQ94DW1rxlIOBRayZVdLlsoGo4"
        originalRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $authToken")
            .method(originalRequest.method, originalRequest.body)
            .build()
        return try {
            chain.proceed(originalRequest)
        } catch (e: Exception) {
            Timber.e("Authorization Exception handle: ${e.javaClass.name}")
            Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body("{${e}}".toResponseBody(null))
                .code(999)
                .message("${e.localizedMessage} ?: Network Request Failed")
                .build()
        }
    }
}