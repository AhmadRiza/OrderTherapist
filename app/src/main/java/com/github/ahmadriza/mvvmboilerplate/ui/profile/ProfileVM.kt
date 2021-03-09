package com.github.ahmadriza.mvvmboilerplate.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository

class ProfileVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    private val _requestLogOut = MutableLiveData<Boolean>()
    val isLoggedOut = Transformations.switchMap(_requestLogOut) {
        repository.logOut()
    }

    private val _refreshUser = MutableLiveData<Boolean>()
    val user = Transformations.switchMap(_refreshUser) { repository.getUser() }

    fun logOut() {
        _requestLogOut.value = true
    }

    fun refreshUser() {
        _refreshUser.value = true
    }

}