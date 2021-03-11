package com.github.ahmadriza.mvvmboilerplate.ui.order.detail

import android.transition.TransitionManager
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrderDetailBinding
import com.github.ahmadriza.mvvmboilerplate.models.OrderStatus
import com.github.ahmadriza.mvvmboilerplate.ui.order.rate.RateOrderFragment
import com.github.ahmadriza.mvvmboilerplate.utils.*
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.google.android.gms.maps.model.LatLng
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

        binding.btnWhatsapp.setOnClickListener {
            val phone = vm.order.value?.data?.data?.therapist?.phone ?: return@setOnClickListener
            context?.whatsappDirect(phone)
        }

        binding.swipe.setOnRefreshListener { vm.refresh() }
        binding.btnMaps.setOnClickListener {

            val lat = vm.order.value?.data?.data?.latitude ?: return@setOnClickListener
            val lon = vm.order.value?.data?.data?.longitude ?: return@setOnClickListener

            context?.openMaps(LatLng(lat.toDouble(), lon.toDouble()))

        }

    }

    override fun initObservers() {
        vm.order.observe(viewLifecycleOwner) {

            binding.swipe.isRefreshing = it.status == Resource.Status.LOADING

            it.data?.data?.let {
                binding.title.text = "Pesanan #${it.number}"
                binding.tvProductName.text = it.product.name
                binding.tvProductPrice.text = it.product.price.formatCurrency()
                binding.imgProduct.loadImage(it.product.thumbnail)
                binding.tvDate.text = it.date.displayDate(requireContext())
                binding.tvTherapistName.text = it.therapist?.name
                binding.imgTheraphist.loadImage(it.therapist?.avatar.toString())

                binding.tvStatus.text = statusData[it.status]?.first
                binding.tvStatusInfo.text = statusData[it.status]?.second

                when (it.status) {
                    OrderStatus.process -> {
                        TransitionManager.beginDelayedTransition(binding.viewRoot)
                        binding.viewTherapist.visible()
                    }
                    OrderStatus.success -> {
                        binding.btnWhatsapp.gone()
                        binding.btnRating.visible()
                    }

                    OrderStatus.canceled -> {
                        TransitionManager.beginDelayedTransition(binding.viewRoot)
                        binding.viewTherapist.gone()
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

    private val statusData = mapOf(
        OrderStatus.pending to Pair(
            "Menunggu Konfirmasi",
            "Kami sedang memeriksa pesanan Anda, kami akan menghubungi Anda melalui Whatsapp untuk konfirmasi lebih lanjut"
        ),
        OrderStatus.canceled to Pair(
            "Pesanan Dibatalkan",
            "Mohon maaf, pesanan Anda telah dibatalkan. Silahkan membuat pesan kembali dan nikmati layanan kami"
        ),
        OrderStatus.success to Pair(
            "Pesanan Selesai",
            "Terimakasih telah memesan layanan kami. Silahkan beri penilaian Anda kepada terapis kami"
        ),
        OrderStatus.process to Pair(
            "Terapis Menuju Lokasi",
            "Terapis sedang menuju ke tempat Anda. Silahkan tunggu di lokasi sesuai alamat pemesanan"
        )
    )

}