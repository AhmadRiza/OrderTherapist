package com.github.ahmadriza.mvvmboilerplate.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.ActivityMainNavBinding
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseActivity
import com.github.ahmadriza.mvvmboilerplate.utils.gone
import com.github.ahmadriza.mvvmboilerplate.utils.makeStatusBarTransparent
import com.github.ahmadriza.mvvmboilerplate.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainNavBinding>(),
    NavController.OnDestinationChangedListener {

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

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {

            R.id.navigation_home,
            R.id.navigation_notifications -> {
                binding.bottomAppBar.visible()
            }

            else -> {
                binding.bottomAppBar.gone()
            }

        }
    }


    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(this)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(this)
    }

}