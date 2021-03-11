package com.github.ahmadriza.mvvmboilerplate.utils

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.github.ahmadriza.mvvmboilerplate.R

fun AppCompatImageView.loadRoundImage(url: String?, placeholder: Int = R.drawable.therapist) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .circleCrop()
        .into(this)
}


fun AppCompatImageView.loadRoundImage(@DrawableRes res: Int) {
    Glide.with(context)
        .load(res)
        .circleCrop()
        .into(this)
}

fun AppCompatImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

