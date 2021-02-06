package com.github.ahmadriza.mvvmboilerplate.data

import com.github.ahmadriza.mvvmboilerplate.models.Order
import com.github.ahmadriza.mvvmboilerplate.models.Product
import com.github.ahmadriza.mvvmboilerplate.models.Therapist
import com.github.ahmadriza.mvvmboilerplate.models.User

object Dummy {

    val user = User(
        name = "John Doe",
        phone = "081237362552",
        address = "Jln. Pattimura No. 18 Malang "
    )

    val products = listOf(

        Product(
            name = "Pijat Refleksi",
            description = "Pijat, pijit, atau urut adalah metode penyembuhan atau terapi kesehatan tradisional, dengan cara memberikan tekanan kepada tubuh  – baik secara terstruktur, tidak terstruktur, menetap, atau berpindah tempat – dengan memberikan tekanan, gerakan, atau getaran, baik dilakukan secara manual ataupun menggunakan alat mekanis.",
            duration = "Jam",
            price = "100000",
            status = "aktif",
            thumbnail = ""
        ),
        Product(
            name = "Pijat Refleksi",
            description = "Pijat, pijit, atau urut adalah metode penyembuhan atau terapi kesehatan tradisional, dengan cara memberikan tekanan kepada tubuh  – baik secara terstruktur, tidak terstruktur, menetap, atau berpindah tempat – dengan memberikan tekanan, gerakan, atau getaran, baik dilakukan secara manual ataupun menggunakan alat mekanis.",
            duration = "Jam",
            price = "100000",
            status = "aktif",
            thumbnail = ""
        ),
        Product(
            name = "Pijat Refleksi",
            description = "Menyembuhkan pegel linu ",
            duration = "Jam",
            price = "100000",
            status = "aktif",
            thumbnail = ""
        ),
        Product(
            name = "Pijat Refleksi",
            description = "Menyembuhkan pegel linu",
            duration = "Jam",
            price = "100000",
            status = "aktif",
            thumbnail = ""
        ),


        )


    val therapist = Therapist(
        name = "Selena Gomez", phone = "0896261551", photo = ""
    )


    val order = Order(
        number = "155526",
        date = "17 Januari 2020",
        status = "Menunggu Konfirmasi",
        info = "Kami sedang memeriksa pesanan Anda, kami akan menghubungi Anda melalui Whatsapp untuk konfirmasi lebih lanjut",
        product = products[0],
        therapist = therapist,
        user = user,
        id = ""
    )
}