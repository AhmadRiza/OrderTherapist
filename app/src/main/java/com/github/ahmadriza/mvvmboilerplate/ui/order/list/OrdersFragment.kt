package com.github.ahmadriza.mvvmboilerplate.ui.order.list

import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrdersBinding
import com.github.ahmadriza.mvvmboilerplate.models.Order
import com.github.ahmadriza.mvvmboilerplate.utils.android.Pager2Adapter
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : BaseFragment<FragmentOrdersBinding>(), OrderAdapter.Listener {

//    private val adapter by lazy { Pager2Adapter(requireActivity()) }

    override fun getLayoutResource(): Int = R.layout.fragment_orders

    override fun initViews() {

    }

    override fun initObservers() {

    }

    override fun initData() {
        val adapter = Pager2Adapter(requireActivity())
        adapter.addFragment(OrderListFragment.getInstance(true, this), "Berjalan")
        adapter.addFragment(OrderListFragment.getInstance(false, this), "Selesai")
        binding.container.adapter = adapter
        TabLayoutMediator(binding.tab, binding.container) { tab, i ->
            tab.text = adapter.getTitle(i)
        }.attach()

    }

    override fun onOrderClicked(order: Order) {
        findNavController().navigate(R.id.action_navigation_orders_to_orderDetailFragment)
    }
}