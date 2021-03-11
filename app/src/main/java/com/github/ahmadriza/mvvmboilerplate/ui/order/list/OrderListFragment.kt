package com.github.ahmadriza.mvvmboilerplate.ui.order.list

import androidx.fragment.app.viewModels
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrderListBinding
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.visibleOrGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderListFragment : BaseFragment<FragmentOrderListBinding>() {

    companion object {
        fun getInstance(isActive: Boolean, listener: OrderAdapter.Listener) =
            OrderListFragment().apply {
                this.isActive = isActive
                this.listener = listener
            }

    }

    private var listener: OrderAdapter.Listener? = null
    private var isActive = true
    private val adapter by lazy { OrderAdapter(listener) }
    private val vm: OrderListVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_order_list

    override fun initViews() {
        binding.rvOrders.adapter = adapter
        binding.swipe.setOnRefreshListener { vm.loadOrder(isActive) }
    }

    override fun initObservers() {
        vm.orders.observe(viewLifecycleOwner) {
            adapter.submitList(it.data?.data)

            binding.swipe.isRefreshing = it.status == Resource.Status.LOADING
            binding.tvEmpty.visibleOrGone(it.data?.data?.isEmpty() ?: true)
        }
    }

    override fun initData() {
        vm.loadOrder(isActive)
    }

}