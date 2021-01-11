package com.github.ahmadriza.mvvmboilerplate.data.local

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */
class SharedPreferenceHelper @Inject constructor (private val sharedPreferences: SharedPreferences) {

    fun saveToken(token: String) = sharedPreferences.edit().putString("token", token).apply()
    fun getToken() = sharedPreferences.getString("token", null)



}