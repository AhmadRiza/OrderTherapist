package com.github.ahmadriza.mvvmboilerplate.models

import com.google.gson.annotations.SerializedName

data class Order(

    val id: String,
    @SerializedName("order_number") val number: String,
    @SerializedName("order_date") val date: String,
    @SerializedName("total_price") val price: String,
    @SerializedName("order_status") var status: String,
    @SerializedName("customer_address") var address: String,
    @SerializedName("latitude") var latitude: String,
    @SerializedName("longitude") var longitude: String,
    @SerializedName("service") val product: Product,
    @SerializedName("terapis_status") val info: String?

)

data class OrderDetail(
    val id: String,
    @SerializedName("order_number") val number: String,
    @SerializedName("order_date") val date: String,
    @SerializedName("total_price") val price: String,
    @SerializedName("order_status") var status: String,
    @SerializedName("customer_address") var address: String,
    @SerializedName("latitude") var latitude: String,
    @SerializedName("longitude") var longitude: String,
    @SerializedName("service") val product: Product,
    @SerializedName("terapis_status") val info: String,
    val feedback: OrderFeedback?,
    @SerializedName("terapis") val therapist: Therapist

)

data class OrderFeedback(
    val feedback: String,
    val rating: Int
)

data class OrderFeedbackRequest(
    @SerializedName("order_id") val orderId: String,
    var feedback: String,
    var rating: Int
)