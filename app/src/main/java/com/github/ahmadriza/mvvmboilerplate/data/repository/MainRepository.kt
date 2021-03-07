package com.github.ahmadriza.mvvmboilerplate.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.github.ahmadriza.mvvmboilerplate.data.local.LocalDataSource
import com.github.ahmadriza.mvvmboilerplate.data.remote.RemoteDataSource
import com.github.ahmadriza.mvvmboilerplate.models.*
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.data.performOperation
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

    fun login(
        request: LoginRequest
    ): LiveData<Resource<LoginResponse>> = performOperation({
        remote.login(request)
    }) {
        local.saveToken(it.token)
        local.saveUser(it.data)
    }

    fun getUser() = liveData(Dispatchers.IO) {
        local.getUser()?.let {
            emit(it)
        }
    }

    fun logOut() = liveData(Dispatchers.IO) {
        local.logOut()
        emit(true)
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

    fun getOrderHistory(): LiveData<Resource<List<Order>>> = liveData(Dispatchers.IO) {

    }

}