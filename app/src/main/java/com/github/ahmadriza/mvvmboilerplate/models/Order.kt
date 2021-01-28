package com.github.ahmadriza.mvvmboilerplate.models

import com.google.gson.annotations.SerializedName

data class Order(

    val id: String,
    @SerializedName("order_number") val number: String,
    @SerializedName("order_date") val date: String,
    @SerializedName("customer_address") val address: String,
    @SerializedName("customer_address") val serviceId: String,


    )