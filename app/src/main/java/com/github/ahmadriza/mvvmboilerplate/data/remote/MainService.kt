package com.github.ahmadriza.mvvmboilerplate.data.remote

import com.github.ahmadriza.mvvmboilerplate.models.LoginRequest
import com.github.ahmadriza.mvvmboilerplate.models.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface MainService {

    @POST("")
    fun login(request: LoginRequest): Response<LoginResponse>

}