package com.github.ahmadriza.mvvmboilerplate.data.repository

import com.github.ahmadriza.mvvmboilerplate.data.local.LocalDataSource
import com.github.ahmadriza.mvvmboilerplate.utils.data.performOperation
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class MainRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {

}