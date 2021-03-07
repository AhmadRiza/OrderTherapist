package com.github.ahmadriza.mvvmboilerplate.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository
import com.github.ahmadriza.mvvmboilerplate.models.LoginRequest

class LoginVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    private val _request = MutableLiveData<LoginRequest>()
    val user = repository.getUser()

    val response = Transformations.switchMap(_request) {
        repository.login(it)
    }

    fun login(email: String, password: String) {
        _request.value = LoginRequest(
            email, password
        )
    }

}