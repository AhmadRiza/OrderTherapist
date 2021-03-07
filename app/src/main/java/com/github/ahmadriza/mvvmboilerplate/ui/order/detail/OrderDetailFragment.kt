package com.github.ahmadriza.mvvmboilerplate.ui.order.detail

import android.transition.TransitionManager
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrderDetailBinding
import com.github.ahmadriza.mvvmboilerplate.ui.order.rate.RateOrderFragment
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.formatCurrency
import com.github.ahmadriza.mvvmboilerplate.utils.gone
import com.github.ahmadriza.mvvmboilerplate.utils.loadRoundImage
import com.github.ahmadriza.mvvmboilerplate.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment<FragmentOrderDetailBinding>() {

    private val vm: OrderDetailVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_order_detail

    override fun initViews() {
        binding.btnRating.setOnClickListener {
            findNavController().navigate(
                R.id.action_orderDetailFragment_to_rateOrderFragment, bundleOf(
                    Pair(RateOrderFragment.EXTRA_THERAPIST, vm.order.value?.data?.therapist)
                )
            )
        }

        binding.swipe.setOnRefreshListener { vm.loadOrder("") }

    }

    override fun initObservers() {
        vm.order.observe(viewLifecycleOwner) {

            it.data?.let {
                binding.title.text = "Pesanan #${it.number}"
                binding.tvProductName.text = it.product.name
                binding.tvProductPrice.text = it.product.price.formatCurrency()
                binding.imgProduct.loadRoundImage(R.drawable.spa)
                binding.tvStatus.text = it.status
                binding.tvStatusInfo.text = it.info
                binding.tvDate.text = it.date
//                binding.tvName.text = it.user.name
//                binding.tvAddress.text = it.user.address
//                binding.tvPhone.text = it.user.phone
                binding.tvTherapistName.text = it.therapist?.name


                when (it.status) {

                    "Menuju Lokasi" -> {
                        TransitionManager.beginDelayedTransition(binding.viewRoot)
                        binding.viewTherapist.visible()
                    }

                    "Pesanan Selesai" -> {
                        binding.btnWhatsapp.gone()
                        binding.btnRating.visible()
                    }
                }

            }

            binding.swipe.isRefreshing = it.status == Resource.Status.LOADING

        }
    }

    override fun initData() {
        vm.loadOrder("")
    }
}