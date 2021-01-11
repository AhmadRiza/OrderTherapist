package com.github.ahmadriza.mvvmboilerplate.utils.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource.Status.ERROR
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource.Status.SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

/*
* Perform get saved data then update it the remote
* */

fun <T, A> performLazyGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <T> performApiCall(
    networkCall: suspend () -> Resource<T>,
    saveCallResult: suspend (T) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val responseStatus = networkCall.invoke()

        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)
            emit(responseStatus)
        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }

    }




fun <T> performOperation(
    operation: suspend () -> Resource<T>, delayTime: Long = 0L
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        delay(delayTime)
        val responseStatus = operation.invoke()
        if (responseStatus.status == SUCCESS) {
            emit(responseStatus)
        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }

    }




