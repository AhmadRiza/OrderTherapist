package com.github.ahmadriza.mvvmboilerplate.data.remote

import com.github.ahmadriza.mvvmboilerplate.models.LoginRequest
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: MainService
) : BaseRemoteDataSource() {

    suspend fun login(request: LoginRequest) = getResult { service.login(request) }

}