package com.github.ahmadriza.mvvmboilerplate.ui.home

import androidx.fragment.app.viewModels
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentHomeBinding
import com.github.ahmadriza.mvvmboilerplate.models.MenuItem
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.formatCurrency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val vm: HomeVM by viewModels()
    private val adapter by lazy { ProductAdapter() }

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun initViews() {

        binding.rvProducts.adapter = adapter

    }

    override fun initObservers() {
        vm.products.observe(viewLifecycleOwner) {
            it.data?.let { adapter.submitList(it) }
        }
        vm.user.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.tvAddress.text = it.address
            binding.tvBalance.text = it.balance.formatCurrency()
        }
    }

    override fun initData() = Unit

    private fun getMenus() = mutableListOf(

        MenuItem("Pesan Pijat", R.drawable.ic_hotel) {

        },
        MenuItem("Pesan Lulur", R.drawable.ic_hotel) {

        },
        MenuItem("Pesan Terapi", R.drawable.ic_hotel) {

        }

    )

}