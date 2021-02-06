package com.github.ahmadriza.mvvmboilerplate.utils

import java.text.NumberFormat
import java.util.*


fun String.formatCurrency(): String {

    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

    formatRupiah.maximumFractionDigits = 0
    formatRupiah.minimumFractionDigits = 0

    return formatRupiah.format(this.toDoubleOrNull() ?: 0.0)
}