package com.github.ahmadriza.mvvmboilerplate.models

import com.github.ahmadriza.mvvmboilerplate.utils.toTimeStamp
import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    val name: String,
    val address: String,
    val age: String,
    val gender: String,
    @SerializedName("phone_number") val phone: String,
    @SerializedName("user_id") val id: String,
    @SerializedName("created_at") val createdAt: String = Date().toTimeStamp(),
    val email: String
)

data class UserRegisterRequest(
    val name: String,
    val address: String,
    val age: String,
    val gender: String,
    @SerializedName("phone_number") val phone: String,
    val email: String,
    val password: String,
    @SerializedName("password_confirmation") val passwordConfirm: String,
) {

    fun isValid(): Boolean {

        return name.isNotBlank() && address.isNotBlank() && age.isNotBlank() &&
                (gender == "laki-laki" || gender == "perempuan") && phone.isNotBlank() &&
                email.isNotBlank() && password.isNotBlank() && (password == passwordConfirm)

    }


}

data class UserLoginRequest(
    val email: String, val password: String
)


data class LoginResponse(
    @SerializedName("access_token") val token: String,
    @SerializedName("expires_in") val tokenExpire: Int,
    val data: User
)

data class UserForgotPasswordResponse(
    val message: String
)