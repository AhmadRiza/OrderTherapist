package com.github.ahmadriza.mvvmboilerplate.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class User(
    val id: String = "",
    val name: String,
    val address: String,
    val phone: String,
    val balance: String = ""
)

@Parcelize
data class Therapist(
    val id: String = "",
    val name: String,
    val photo: String,
    val phone: String
) : Parcelable