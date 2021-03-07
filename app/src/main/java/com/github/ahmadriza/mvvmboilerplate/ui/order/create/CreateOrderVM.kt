package com.github.ahmadriza.mvvmboilerplate.ui.order.create

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository
import com.github.ahmadriza.mvvmboilerplate.models.ProductCheckoutRequest
import com.google.android.gms.maps.model.LatLng

class CreateOrderVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    private lateinit var productId: String
    var orderLocation: LatLng? = null

    val user = repository.getUser()

    private val _orderRequest = MutableLiveData<ProductCheckoutRequest>()
    val response = Transformations.switchMap(_orderRequest) { repository.checkOut(it) }

    fun setProductId(id: String) {
        this.productId = id
    }

    fun setLocation(latLng: LatLng) {
        orderLocation = latLng
    }

    fun chekout(address: String) {

        val loc = orderLocation ?: return

        val request = ProductCheckoutRequest(
            address = address,
            latitude = loc.latitude.toString(),
            longitude = loc.longitude.toString(),
            id = productId
        )
        _orderRequest.value = request
    }

}