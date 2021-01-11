package com.github.ahmadriza.mvvmboilerplate.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.github.ahmadriza.mvvmboilerplate.utils.android.DelayTextWatcher
import org.jetbrains.anko.runOnUiThread

/**
 * Created on 11/28/20.
 */

fun Context.getCompatColor(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun View.disable() {
    isEnabled = false
}

fun View.enable() {
    isEnabled = true
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.isAnValidEmail(): Boolean {
    return if (isNullOrBlank()) {
        false
    } else {
        android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}

fun EditText.addOnSubmitListener(delay: Long, onSubmit: (s: String) -> Unit) {
    addTextChangedListener(DelayTextWatcher(delay) {
        context.runOnUiThread { onSubmit.invoke(it) }
    })
}

fun AppCompatTextView.setDrawableIcon(
    leftDrawable: Int = 0, topDrawable: Int = 0,
    rightDrawable: Int = 0, bottomDrawable: Int = 0
) {
    setCompoundDrawablesWithIntrinsicBounds(
        leftDrawable, topDrawable, rightDrawable, bottomDrawable
    )
}

fun AppCompatEditText.setDrawableIcon(
    leftDrawable: Int = 0, topDrawable: Int = 0,
    rightDrawable: Int = 0, bottomDrawable: Int = 0
) {
    setCompoundDrawablesWithIntrinsicBounds(
        leftDrawable, topDrawable, rightDrawable, bottomDrawable
    )
}


fun View.getYPosition(activity: Activity, rootView: View): Int{

    val dm = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(dm)
    val topOffset = dm.heightPixels - rootView.measuredHeight

    val loc = intArrayOf(0,0)
    getLocationOnScreen(loc)

    return loc[1] - topOffset

}


