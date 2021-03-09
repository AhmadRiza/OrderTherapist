package com.github.ahmadriza.mvvmboilerplate.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository

class HomeVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    private val _refreshUser = MutableLiveData<Boolean>()
    val products = repository.getProducts()
    val user = Transformations.switchMap(_refreshUser) {
        repository.getUser()
    }

    fun refreshUser() {
        _refreshUser.value = true
    }

}