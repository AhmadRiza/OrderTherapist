package com.github.ahmadriza.mvvmboilerplate.data.local

import com.github.ahmadriza.mvvmboilerplate.data.remote.interceptor.AuthInterceptor
import com.github.ahmadriza.mvvmboilerplate.models.User
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class LocalDataSource @Inject constructor(
    private val pref: SharedPreferenceHelper,
    private val auth: AuthInterceptor
) : BaseLocalDataSource() {

    fun getToken(): String? = pref.getToken()
    fun saveToken(token: String) {
        auth.setToken(token)
        pref.saveToken(token)
    }

    fun saveUser(user: User) = pref.saveUser(user)
    fun getUser(): User? = pref.getUser()

    fun logOut() = pref.logOut()

}