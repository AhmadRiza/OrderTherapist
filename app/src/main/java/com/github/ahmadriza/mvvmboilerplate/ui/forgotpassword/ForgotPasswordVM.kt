package com.github.ahmadriza.mvvmboilerplate.ui.forgotpassword

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository
import com.github.ahmadriza.mvvmboilerplate.models.ForgotPasswordRequest

class ForgotPasswordVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    private val _request = MutableLiveData<ForgotPasswordRequest>()
    val response = Transformations.switchMap(_request) {
        repository.forgotPassword(it)
    }

    fun submit(email: String) {
        val request = ForgotPasswordRequest(email)
        _request.value = request
    }

}