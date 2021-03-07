package com.github.ahmadriza.mvvmboilerplate.ui.order.create

import android.app.Activity
import android.content.Intent
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrderBinding
import com.github.ahmadriza.mvvmboilerplate.models.Product
import com.github.ahmadriza.mvvmboilerplate.ui.location.LocationPickerActivity
import com.github.ahmadriza.mvvmboilerplate.utils.*
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class CreateOrderFragment : BaseFragment<FragmentOrderBinding>() {

    companion object {
        const val EXTRA_PRODUCT = "product"
        const val RC_LOCATION = 1
    }

    private val vm: CreateOrderVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_order

    override fun initViews() {
        binding.btnOrder.setOnClickListener {

            if (vm.orderLocation == null) {
                context?.toast("Pilih lokasi di map terlebih dahulu")
                return@setOnClickListener
            }

            if (binding.etAddress.text.isNullOrBlank()) {
                context?.toast("Masukkan alamat terlebih dahulu")
                return@setOnClickListener
            }

            vm.chekout(binding.etAddress.text.toString())

        }
        binding.tvLocation.setOnClickListener {
            startActivityForResult(
                LocationPickerActivity.getIntent(requireContext(), vm.orderLocation), RC_LOCATION
            )
        }
    }

    override fun initObservers() {
        vm.user.observe(viewLifecycleOwner) {
            binding.etAddress.setText(it.address)
        }
        vm.response.observe(viewLifecycleOwner) {

            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.loading.visible()
                    binding.btnOrder.disable()
                }
                else -> {
                    binding.loading.gone()
                    binding.btnOrder.enable()

                    if (it.status == Resource.Status.SUCCESS) {
                        findNavController().navigate(
                            R.id.action_createOrderFragment_to_orderDetailFragment, bundleOf(
                                Pair("id", it.data!!.data!!.id)
                            )
                        )

                    } else {
                        context?.toast(it.message.toString())
                    }

                }
            }

        }
    }

    override fun initData() {
        arguments?.getParcelable<Product>(EXTRA_PRODUCT)?.let {
            vm.setProductId(it.id)
            binding.tvProductName.text = it.name
            binding.tvProductDescription.text = it.description
            binding.tvProductPrice.text = it.price.formatCurrency()
            binding.tvTotalPrice.text = it.price.formatCurrency()
            binding.imgProduct.loadRoundImage(it.thumbnail)
            binding.tvDuration.text = "/${it.duration} menit"
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) return

        when (requestCode) {

            RC_LOCATION -> {
                data?.getParcelableExtra<LatLng>(LocationPickerActivity.EXTRA_LAT_LNG)?.let {
                    vm.setLocation(it)
                    binding.tvLocation.text = "${it.latitude}, ${it.longitude}"
                }
            }

        }


    }
}