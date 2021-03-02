package com.github.ahmadriza.mvvmboilerplate.data

import com.github.ahmadriza.mvvmboilerplate.models.*

object Dummy {

    val user = User(
        name = "John Doe",
        phone = "081237362552",
        address = "Jln. Pattimura No. 18 Malang ",
        balance = "1250000",
        email = "joni@gmail.com",
        gender = "laki-laki",
        id = ""
    )

    val category = ProductCategory(
        name = "Gold", id = "", thumbnail = ""
    )

    val products = listOf(

        Product(
            name = "Pijat Refleksi",
            description = "Pijat, pijit, atau urut adalah metode penyembuhan atau terapi kesehatan tradisional, dengan cara memberikan tekanan kepada tubuh  – baik secara terstruktur, tidak terstruktur, menetap, atau berpindah tempat – dengan memberikan tekanan, gerakan, atau getaran, baik dilakukan secara manual ataupun menggunakan alat mekanis.",
            duration = "Jam",
            price = "100000",
            status = "aktif",
            thumbnail = "",
            category = category
        ),
        Product(
            name = "Pijat Refleksi",
            description = "Pijat, pijit, atau urut adalah metode penyembuhan atau terapi kesehatan tradisional, dengan cara memberikan tekanan kepada tubuh  – baik secara terstruktur, tidak terstruktur, menetap, atau berpindah tempat – dengan memberikan tekanan, gerakan, atau getaran, baik dilakukan secara manual ataupun menggunakan alat mekanis.",
            duration = "Jam",
            price = "100000",
            status = "aktif",
            thumbnail = "",
            category = category
        ),
        Product(
            name = "Pijat Refleksi",
            description = "Pijat, pijit, atau urut adalah metode penyembuhan atau terapi kesehatan tradisional, dengan cara memberikan tekanan kepada tubuh  – baik secara terstruktur, tidak terstruktur, menetap, atau berpindah tempat – dengan memberikan tekanan, gerakan, atau getaran, baik dilakukan secara manual ataupun menggunakan alat mekanis.",
            duration = "Jam",
            price = "100000",
            status = "aktif",
            thumbnail = "",
            category = category
        ),
        Product(
            name = "Pijat Refleksi",
            description = "Pijat, pijit, atau urut adalah metode penyembuhan atau terapi kesehatan tradisional, dengan cara memberikan tekanan kepada tubuh  – baik secara terstruktur, tidak terstruktur, menetap, atau berpindah tempat – dengan memberikan tekanan, gerakan, atau getaran, baik dilakukan secara manual ataupun menggunakan alat mekanis.",
            duration = "Jam",
            price = "100000",
            status = "aktif",
            thumbnail = "",
            category = category
        )


    )


    val therapist = Therapist(
        name = "Selena Gomez",
        phone = "0896261551",
        avatar = "",
        id = "",
        gender = "perempuan",
        age = 22
    )


    val order = OrderDetail(
        number = "155526",
        date = "17 Januari 2020",
        status = "Menunggu Konfirmasi",
        info = "Kami sedang memeriksa pesanan Anda, kami akan menghubungi Anda melalui Whatsapp untuk konfirmasi lebih lanjut",
        product = products[0],
        therapist = therapist,
        address = "Jauh",
        feedback = null,
        latitude = "111.2929",
        longitude = "99191",
        price = "200.000",
        id = ""
    )
}