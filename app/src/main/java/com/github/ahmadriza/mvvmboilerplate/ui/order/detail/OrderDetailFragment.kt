package com.github.ahmadriza.mvvmboilerplate.ui.order.detail

import android.transition.TransitionManager
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrderDetailBinding
import com.github.ahmadriza.mvvmboilerplate.ui.order.rate.RateOrderFragment
import com.github.ahmadriza.mvvmboilerplate.utils.*
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment<FragmentOrderDetailBinding>() {

    private val vm: OrderDetailVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_order_detail

    override fun initViews() {
        binding.btnRating.setOnClickListener {
            val therapist = vm.order.value?.data?.data?.therapist ?: return@setOnClickListener
            val id = vm.order.value?.data?.data?.id ?: return@setOnClickListener
            findNavController().navigate(
                R.id.action_orderDetailFragment_to_rateOrderFragment, bundleOf(
                    Pair(RateOrderFragment.EXTRA_THERAPIST, therapist),
                    Pair(RateOrderFragment.EXTRA_ID, id)
                )
            )
        }

        binding.swipe.setOnRefreshListener { vm.refresh() }

    }

    override fun initObservers() {
        vm.order.observe(viewLifecycleOwner) {

            binding.swipe.isRefreshing = it.status == Resource.Status.LOADING

            it.data?.data?.let {
                binding.title.text = "Pesanan #${it.number}"
                binding.tvProductName.text = it.product.name
                binding.tvProductPrice.text = it.product.price.formatCurrency()
                binding.imgProduct.loadRoundImage(R.drawable.spa)
                binding.tvStatus.text = it.status
                binding.tvStatusInfo.text = it.info
                binding.tvDate.text = it.date
                binding.tvTherapistName.text = it.therapist?.name
                binding.imgTheraphist.loadImage(it.therapist?.avatar.toString())

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


            if (it.status == Resource.Status.ERROR) {
                context?.toast(it.message.toString())
            }

        }

        vm.user.observe(viewLifecycleOwner) {
            it.data?.let {
                binding.tvName.text = it.name
                binding.tvAddress.text = it.address
                binding.tvPhone.text = it.phone
            }

        }
    }

    override fun initData() {
        arguments?.getString("id")?.let { vm.loadOrder(it) }
    }
}