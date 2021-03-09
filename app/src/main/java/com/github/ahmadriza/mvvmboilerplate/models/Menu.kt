package com.github.ahmadriza.mvvmboilerplate.models

data class MenuItem(
    val title: String,
    val onClick: () -> Unit
)