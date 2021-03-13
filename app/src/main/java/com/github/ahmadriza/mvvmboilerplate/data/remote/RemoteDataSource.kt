package com.github.ahmadriza.mvvmboilerplate.data.remote

import com.github.ahmadriza.mvvmboilerplate.models.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: MainService
) : BaseRemoteDataSource() {

    suspend fun login(request: LoginRequest) = getResult { service.login(request) }
    suspend fun register(request: RegisterRequest) = getResult { service.register(request) }
    suspend fun editUser(request: EditUserRequest) = getResult { service.editProfile(request) }
    suspend fun forgotPassword(request: ForgotPasswordRequest) =
        getResult { service.forgotPassword(request) }

    suspend fun logout() = getResult { service.logout() }

    suspend fun getUser() = getResult { service.getProfile() }
    suspend fun getProducts() = getResult { service.getAllServices() }

    suspend fun checkOut(request: ProductCheckoutRequest) = getResult { service.checkOut(request) }
    suspend fun getOrderDetail(id: String) = getResult { service.getDetailOrder(id) }
    suspend fun orderFeedback(request: OrderFeedbackRequest) =
        getResult { service.orderFeedback(request) }

    suspend fun getAllOrder() = getResult { service.getAllOrder() }
    suspend fun getActiveOrder() = getResult { service.getActiveAllOrder() }

    suspend fun topUp(topUpRequest: TopUpRequest) = getResult { service.createTopUp(topUpRequest) }
    suspend fun topUpHistory() = getResult { service.getActiveTopUp() }

}