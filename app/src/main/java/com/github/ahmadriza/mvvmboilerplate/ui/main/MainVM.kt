package com.github.ahmadriza.mvvmboilerplate.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository

class MainVM @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun isLoggedin() = repository.isLoggedIn()

}