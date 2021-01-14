package com.github.ahmadriza.mvvmboilerplate.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created on 11/27/20.
 * base activity created to auto create view binding so the code could be simpler, recommend implement base activity to all activity
 */

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), IBaseView {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutResource())
        initViews()
        initObservers()
        initData()
    }




}