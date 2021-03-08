package com.github.ahmadriza.mvvmboilerplate.utils.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.github.ahmadriza.mvvmboilerplate.utils.android.RefreshableLiveData
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource.Status.ERROR
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource.Status.SUCCESS
import kotlinx.coroutines.Dispatchers

/*
* Perform get saved data then update it the remote
* */

fun <T, A> performLazyGetOperation(
    cacheOperation: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = cacheOperation.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <T> performOperation(
    networkCall: suspend () -> Resource<T>,
    saveCallResult: (suspend (T) -> Unit)? = null,
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val responseStatus = networkCall.invoke()

        if (responseStatus.status == SUCCESS) {
            saveCallResult?.invoke(responseStatus.data!!)
            emit(responseStatus)
        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }

    }


fun <T> refreshLiveData(networkCall: suspend () -> Resource<T>) = RefreshableLiveData<Resource<T>> {
    it.post(Resource.loading())
    val response = networkCall.invoke()
    if (response.status == SUCCESS) {
        it.post(response)
    } else if (response.status == ERROR) {
        it.post(Resource.error(response.message!!))
    }
}




