package com.github.ahmadriza.mvvmboilerplate.ui.topup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.ahmadriza.mvvmboilerplate.data.remote.BaseResponse
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository
import com.github.ahmadriza.mvvmboilerplate.models.Invoice
import com.github.ahmadriza.mvvmboilerplate.models.TopUpRequest
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import kotlinx.coroutines.launch

class TopUpVM @ViewModelInject constructor(repository: MainRepository) : ViewModel() {

    private val _topUpRequest = MutableLiveData<TopUpRequest>()
    val response = Transformations.switchMap(_topUpRequest) {
        repository.createTopUp(it)
    }

    private val _history =
        repository.topUpHistory()
    val history: LiveData<Resource<BaseResponse<Invoice>>> = _history

    init {
        refreshHistory()
    }

    fun refreshHistory() = viewModelScope.launch { _history.refresh() }

    fun submitTopUp(nominal: Long) {
        val request = TopUpRequest(nominal.toString())
        _topUpRequest.value = request
    }
}