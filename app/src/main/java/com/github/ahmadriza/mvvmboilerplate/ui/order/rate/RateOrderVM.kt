package com.github.ahmadriza.mvvmboilerplate.ui.order.rate

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository
import com.github.ahmadriza.mvvmboilerplate.models.OrderFeedbackRequest

class RateOrderVM @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    private lateinit var orderId: String

    private val _feedbackRequest = MutableLiveData<OrderFeedbackRequest>()
    val response = Transformations.switchMap(_feedbackRequest) {
        repository.orderFeedback(it)
    }

    fun setId(id: String) {
        this.orderId = id
    }

    fun submitFeedback(comment: String, rating: Int) {

        val request = OrderFeedbackRequest(
            feedback = comment, rating = rating, orderId = orderId
        )
        _feedbackRequest.value = request

    }

}