package com.github.ahmadriza.mvvmboilerplate.data.remote

import com.github.ahmadriza.mvvmboilerplate.models.LoginRequest
import com.github.ahmadriza.mvvmboilerplate.models.ProductCheckoutRequest
import com.github.ahmadriza.mvvmboilerplate.models.RegisterRequest
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: MainService
) : BaseRemoteDataSource() {

    suspend fun login(request: LoginRequest) = getResult { service.login(request) }
    suspend fun register(request: RegisterRequest) = getResult { service.register(request) }
    suspend fun getProducts() = getResult { service.getAllServices() }
    suspend fun checkOut(request: ProductCheckoutRequest) = getResult { service.checkOut(request) }
    suspend fun getOrderDetail(id: String) = getResult { service.getDetailOrder(id) }

}