package com.github.ahmadriza.mvvmboilerplate.ui.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.remote.BaseResponse
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository
import com.github.ahmadriza.mvvmboilerplate.models.RegisterRequest
import com.github.ahmadriza.mvvmboilerplate.models.User
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource

class RegisterVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    private val _request = MutableLiveData<RegisterRequest>()

    val response: LiveData<Resource<BaseResponse<User>>> = Transformations.switchMap(_request) {
        repository.register(it)
    }

    fun register(
        name: String,
        email: String,
        address: String,
        age: Int,
        isMen: Boolean,
        password: String,
        phone: String
    ) {

        val request = RegisterRequest(
            name = name,
            email = email,
            address = address,
            age = age,
            gender = if (isMen) "laki-laki" else "perempuan",
            password = password,
            phone = phone
        )

        _request.value = request
    }


}