package com.github.ahmadriza.mvvmboilerplate.ui.home

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentHomeBinding
import com.github.ahmadriza.mvvmboilerplate.models.Product
import com.github.ahmadriza.mvvmboilerplate.ui.order.create.CreateOrderFragment
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.formatCurrency
import com.github.ahmadriza.mvvmboilerplate.utils.gone
import com.github.ahmadriza.mvvmboilerplate.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), ProductAdapter.Listener {

    private val vm: HomeVM by viewModels()
    private val adapter by lazy { ProductAdapter(this) }

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun initViews() {

        binding.rvProducts.adapter = adapter

    }

    override fun initObservers() {
        vm.products.observe(viewLifecycleOwner) {
            it.data?.let {
                adapter.submitList(it)
                binding.groupContent.visible()
            }
            if (it.status == Resource.Status.LOADING) {
                binding.loading.visible()
            } else {
                binding.loading.gone()
            }
        }

        vm.user.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.tvAddress.text = it.address
            binding.tvBalance.text = it.balance.formatCurrency()
        }
    }

    override fun initData() = Unit


    override fun onProductOrder(product: Product) {
        findNavController().navigate(
            R.id.action_homeFragment_to_createOrderFragment, bundleOf(
                Pair(CreateOrderFragment.EXTRA_PRODUCT, product)
            )
        )
    }

}