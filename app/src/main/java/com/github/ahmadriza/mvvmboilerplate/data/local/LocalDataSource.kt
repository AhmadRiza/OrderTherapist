package com.github.ahmadriza.mvvmboilerplate.data.local

import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class LocalDataSource @Inject constructor(
    private val pref: SharedPreferenceHelper
) : BaseLocalDataSource(){


    suspend fun getToken() = getResult { pref.getToken() }
    suspend fun saveToken(token: String) = pref.saveToken(token)

}