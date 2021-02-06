package com.github.ahmadriza.mvvmboilerplate.ui.order.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository

class OrderDetailVM @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _orderId = MutableLiveData<String>()

    val order = Transformations.switchMap(_orderId) {
        repository.getOrder(it)
    }

    fun loadOrder(id: String) {
        _orderId.value = id
    }

}