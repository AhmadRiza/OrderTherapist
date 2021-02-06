package com.github.ahmadriza.mvvmboilerplate.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.github.ahmadriza.mvvmboilerplate.data.Dummy
import com.github.ahmadriza.mvvmboilerplate.data.local.LocalDataSource
import com.github.ahmadriza.mvvmboilerplate.models.Order
import com.github.ahmadriza.mvvmboilerplate.models.Product
import com.github.ahmadriza.mvvmboilerplate.models.User
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class MainRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    fun getProducts(): LiveData<Resource<List<Product>>> = liveData(Dispatchers.IO) {

        emit(Resource.loading())
        kotlinx.coroutines.delay(1000)
        emit(
            Resource.success(Dummy.products)
        )

    }

    fun getUser(): LiveData<User> = liveData(Dispatchers.IO) {
        emit(Dummy.user)
    }

    fun getOrder(id: String): LiveData<Resource<Order>> = liveData(Dispatchers.IO) {

        val order = Dummy.order

        emit(Resource.loading())
//        kotlinx.coroutines.delay(2000)
        emit(Resource.success(order))
        kotlinx.coroutines.delay(5000)
        order.status = "Menuju Lokasi"
        emit(Resource.success(order))
        kotlinx.coroutines.delay(5000)
        order.status = "Pesanan Selesai"
        emit(Resource.success(order))

    }

}