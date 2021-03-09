package com.github.ahmadriza.mvvmboilerplate.data.remote.interceptor

import com.github.ahmadriza.mvvmboilerplate.data.local.SharedPreferenceHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by on 11/25/20.
 */
class AuthInterceptor @Inject constructor(pref: SharedPreferenceHelper) :
    Interceptor {

    private var token: String? = pref.getToken()

    fun setToken(token: String?) {
        this.token = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val req = chain.request()
        val builder = req.newBuilder()

        builder.header("Authorization", "Bearer $token")

        return chain.proceed(builder.build())
    }

}