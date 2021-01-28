package com.github.ahmadriza.mvvmboilerplate.models

import com.google.gson.annotations.SerializedName

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val duration: String,
    val price: String,
    val thumbnail: String,
    val status: String,
) {

    fun isActive() = status == "aktif"

}

data class ProductCheckoutRequest(
    @SerializedName("customer_address") val address: String,
    @SerializedName("service_id") val id: String,
    val latitude: String,
    val longitude: String
)

