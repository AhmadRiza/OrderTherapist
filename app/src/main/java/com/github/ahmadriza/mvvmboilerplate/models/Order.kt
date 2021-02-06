package com.github.ahmadriza.mvvmboilerplate.models

data class Order(

    val id: String,
    val number: String,
    val date: String,
    var status: String,
    var info: String,
    val product: Product,
    val user: User,
    var therapist: Therapist?

)