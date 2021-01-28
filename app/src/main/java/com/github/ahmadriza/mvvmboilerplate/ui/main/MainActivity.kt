package com.github.ahmadriza.mvvmboilerplate.ui.main

import androidx.activity.viewModels
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.ActivityMainBinding
import com.github.ahmadriza.mvvmboilerplate.ui.menu.MenuFragment
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: SplashViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun initViews() {

        supportFragmentManager.beginTransaction().replace(
            R.id.drawer_fragment_container, MenuFragment()
        ).commit()

    }

    override fun initObservers() {
    }

    override fun initData() {
    }

}