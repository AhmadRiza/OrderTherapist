package com.github.ahmadriza.mvvmboilerplate.data.remote

data class BaseResponse<T>(
    val status: String,
    val message: String?,
    val data: T?
) {

    fun isSuccess() = status == "success" && data != null

}
