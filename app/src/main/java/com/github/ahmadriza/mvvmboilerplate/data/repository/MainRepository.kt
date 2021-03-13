package com.github.ahmadriza.mvvmboilerplate.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.github.ahmadriza.mvvmboilerplate.data.local.LocalDataSource
import com.github.ahmadriza.mvvmboilerplate.data.remote.RemoteDataSource
import com.github.ahmadriza.mvvmboilerplate.models.*
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.data.performLazyGetOperation
import com.github.ahmadriza.mvvmboilerplate.utils.data.performOperation
import com.github.ahmadriza.mvvmboilerplate.utils.data.refreshLiveData
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class MainRepository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {

    fun isLoggedIn() = local.getToken() != null

    fun register(
        request: RegisterRequest
    ) = performOperation({ remote.register(request) }) {
        local.saveUser(it.data!!)
    }

    fun editUser(
        request: EditUserRequest
    ) = performOperation({ remote.editUser(request) }) {
        local.saveUser(it.data!!)
    }


    fun login(
        request: LoginRequest
    ): LiveData<Resource<LoginResponse>> = performOperation({
        remote.login(request)
    }) {
        local.saveToken(it.token)
        local.saveUser(it.data)
    }

    fun forgotPassword(request: ForgotPasswordRequest) = performOperation({
        remote.forgotPassword(request)
    })

    fun getUser() = performLazyGetOperation(
        cacheOperation = { local.getUser() },
        networkCall = { remote.getUser() },
        saveCallResult = {
            local.saveUser(it.data!!)
        }
    )

    fun logOut() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        local.logOut()
        remote.logout()
        emit(Resource.success(true))
    }

    fun getProducts() = performOperation({
        remote.getProducts()
    })

    fun checkOut(request: ProductCheckoutRequest) = performOperation({
        remote.checkOut(request)
    })

    fun getOrder(id: String) = performOperation({
        remote.getOrderDetail(id)
    })

    fun orderFeedback(feedbackRequest: OrderFeedbackRequest) = performOperation({
        remote.orderFeedback(feedbackRequest)
    })

    fun getAllOrder() = performOperation({
        remote.getAllOrder()
    })

    fun getActiveOrder() = performOperation({
        remote.getActiveOrder()
    })

    fun createTopUp(request: TopUpRequest) = performOperation({
        remote.topUp(request)
    })

    fun topUpHistory() = refreshLiveData { remote.topUpHistory() }

}