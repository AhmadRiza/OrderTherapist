package com.github.ahmadriza.mvvmboilerplate.models


data class User(
    val id: String = "",
    val name: String,
    val address: String,
    val phone: String,
    val balance: String = ""
)

data class Therapist(
    val id: String = "",
    val name: String,
    val photo: String,
    val phone: String
)