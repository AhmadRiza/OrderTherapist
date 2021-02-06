package com.github.ahmadriza.mvvmboilerplate.ui.order.create

import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrderBinding
import com.github.ahmadriza.mvvmboilerplate.models.Product
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.formatCurrency
import com.github.ahmadriza.mvvmboilerplate.utils.loadRoundImage

class CreateOrderFragment : BaseFragment<FragmentOrderBinding>() {

    companion object {
        const val EXTRA_PRODUCT = "product"
    }

    override fun getLayoutResource(): Int = R.layout.fragment_order

    override fun initViews() {
    }

    override fun initObservers() {
    }

    override fun initData() {
        arguments?.getParcelable<Product>(EXTRA_PRODUCT)?.let {
            binding.tvProductName.text = it.name
            binding.tvProductDescription.text = it.description
            binding.tvProductPrice.text = it.price.formatCurrency()
            binding.tvTotalPrice.text = it.price.formatCurrency()
            binding.imgProduct.loadRoundImage(R.drawable.spa)
        }
    }
}