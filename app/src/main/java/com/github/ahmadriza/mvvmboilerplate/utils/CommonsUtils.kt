package com.github.ahmadriza.mvvmboilerplate.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.android.gms.maps.model.LatLng
import org.jetbrains.anko.toast


fun Context.whatsappDirect(number: String) {
    try {
        var target = number

        if (target.startsWith("0")) target = "+62${target.substring(1, number.length)}"

        val url = "https://api.whatsapp.com/send?phone=$target"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    } catch (e: Exception) {
        toast("Nomor telefon tidak terdaftar WA")
    }

}

fun Context.openMaps(latLng: LatLng) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("geo:${latLng.latitude},${latLng.longitude}?q=${latLng.latitude},${latLng.longitude}")
    )
    startActivity(intent)
}