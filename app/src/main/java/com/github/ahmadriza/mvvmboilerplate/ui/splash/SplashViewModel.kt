package com.github.ahmadriza.mvvmboilerplate.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class SplashViewModel @ViewModelInject constructor(
    private val repository: MainRepository
): ViewModel(){


}