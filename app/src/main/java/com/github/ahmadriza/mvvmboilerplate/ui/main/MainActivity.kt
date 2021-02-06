package com.github.ahmadriza.mvvmboilerplate.ui.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.ActivityMainNavBinding
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseActivity
import com.github.ahmadriza.mvvmboilerplate.utils.makeStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainNavBinding>() {

    private lateinit var navController: NavController

    override fun getLayoutResource(): Int = R.layout.activity_main_nav

    override fun initViews() {

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

//        if(viewModel.isLoggedIn) navController.graph = navController.navInflater.inflate(R.navigation.nav_home_registered)
//        else navController.graph = navController.navInflater.inflate(R.navigation.nav_home_unregistered)
        navController.graph = navController.navInflater.inflate(R.navigation.main_navigation)
        binding.navView.setupWithNavController(navController)

        makeStatusBarTransparent()
    }

    override fun initObservers() {
    }

    override fun initData() {
    }

}