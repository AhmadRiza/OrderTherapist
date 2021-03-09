package com.github.ahmadriza.mvvmboilerplate.ui.order.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository

class OrderListVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    private val _isActive = MutableLiveData<Boolean>()

    val orders = Transformations.switchMap(_isActive) {
        if (it) repository.getActiveOrder() else repository.getAllOrder()
    }

    fun loadOrder(isActive: Boolean) {
        _isActive.value = isActive
    }

}