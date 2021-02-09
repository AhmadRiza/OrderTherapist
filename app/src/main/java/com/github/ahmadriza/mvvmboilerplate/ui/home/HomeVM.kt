package com.github.ahmadriza.mvvmboilerplate.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository

class HomeVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    val products = repository.getProducts()
    val user = repository.getUser()

}