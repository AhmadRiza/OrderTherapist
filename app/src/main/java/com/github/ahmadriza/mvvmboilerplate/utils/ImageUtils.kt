package com.github.ahmadriza.mvvmboilerplate.utils

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadRoundImage(url: String) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(this)
}


fun AppCompatImageView.loadRoundImage(@DrawableRes res: Int) {
    Glide.with(context)
        .load(res)
        .circleCrop()
        .into(this)
}