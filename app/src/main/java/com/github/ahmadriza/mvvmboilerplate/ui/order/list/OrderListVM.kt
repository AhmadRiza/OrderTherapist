package com.github.ahmadriza.mvvmboilerplate.ui.order.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository

class OrderListVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {


    val orders = repository.getOrderHistory()


}