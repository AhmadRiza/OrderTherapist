package com.github.ahmadriza.mvvmboilerplate.data.local

import android.content.SharedPreferences
import com.github.ahmadriza.mvvmboilerplate.models.User
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */
class SharedPreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {

    fun saveToken(token: String) = sharedPreferences.edit().putString("token", token).apply()
    fun getToken() = sharedPreferences.getString("token", null)
    fun saveUser(user: User) {
        gson.toJson(user).let {
            sharedPreferences.edit().putString("user", it).apply()
        }
    }

    fun getUser(): User? = try {
        gson.fromJson("user", User::class.java)
    } catch (e: JsonSyntaxException) {
        null
    }

}