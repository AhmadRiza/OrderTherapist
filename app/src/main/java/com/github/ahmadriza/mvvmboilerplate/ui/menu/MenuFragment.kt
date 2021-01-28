package com.github.ahmadriza.mvvmboilerplate.ui.menu

import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentMenuBinding
import com.github.ahmadriza.mvvmboilerplate.models.MenuItem
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment

class MenuFragment : BaseFragment<FragmentMenuBinding>() {

    private val menuAdapter by lazy { MenuAdapter() }

    override fun getLayoutResource(): Int = R.layout.fragment_menu

    override fun initViews() {
        binding.rvMenu1.adapter = menuAdapter
    }

    override fun initObservers() {

    }

    override fun initData() {

        menuAdapter.submitList(
            getMenus()
        )

    }

    private fun getMenus() = mutableListOf(

        MenuItem("Pesan Pijat", R.drawable.ic_hotel) {

        },
        MenuItem("Pesan Lulur", R.drawable.ic_hotel) {

        },
        MenuItem("Pesan Terapi", R.drawable.ic_hotel) {

        }

    )

}