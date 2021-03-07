package com.github.ahmadriza.mvvmboilerplate.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by on 11/25/20.
 */
class AuthInterceptor @Inject constructor() :
    Interceptor {

    private var token: String? = null

    fun setToken(token: String) {
        this.token = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val req = chain.request()
        val builder = req.newBuilder()

        if (token != null) builder.header("Authorization", "Bearer $token")

        return chain.proceed(builder.build())
    }
}