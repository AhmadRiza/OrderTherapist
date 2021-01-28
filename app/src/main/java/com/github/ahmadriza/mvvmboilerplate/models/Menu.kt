package com.github.ahmadriza.mvvmboilerplate.models

import androidx.annotation.DrawableRes

data class MenuItem(
    val title: String,
    @DrawableRes val icon: Int,
    val onClick: () -> Unit
)