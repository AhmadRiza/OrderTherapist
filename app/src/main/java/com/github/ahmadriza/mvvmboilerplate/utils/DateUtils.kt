package com.github.ahmadriza.mvvmboilerplate.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on 12/3/20.
 */


fun countWeekPassedFromDate(date: Date): Int {

    val diff = Date().time - date.time

    val secondInMs: Long = 1000
    val minutesInMs: Long = secondInMs * 60
    val hoursInMs: Long = minutesInMs * 60
    val dayInMs: Long = hoursInMs * 24
    val weekInMs: Long = dayInMs * 7

    return (diff / weekInMs).toInt()
}


fun String.toDateOrNull(
    format: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    isUtc: Boolean = true
): Date? {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    if (isUtc) sdf.timeZone = TimeZone.getTimeZone("UTC")

    return try {
        sdf.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.toTimeStamp(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}