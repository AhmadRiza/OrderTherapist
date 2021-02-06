package com.github.ahmadriza.mvvmboilerplate.ui.order.detail

import androidx.fragment.app.viewModels
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrderDetailBinding
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment<FragmentOrderDetailBinding>() {

    private val vm: OrderDetailVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_order_detail

    override fun initViews() {

    }

    override fun initObservers() {
        vm.order.observe(viewLifecycleOwner) {

            it.data?.let {


            }


        }
    }

    override fun initData() {
        vm.loadOrder("")
    }
}