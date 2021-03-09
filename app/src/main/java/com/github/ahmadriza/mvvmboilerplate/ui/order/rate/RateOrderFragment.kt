package com.github.ahmadriza.mvvmboilerplate.ui.order.rate

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrderRatingBinding
import com.github.ahmadriza.mvvmboilerplate.models.Therapist
import com.github.ahmadriza.mvvmboilerplate.utils.*
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class RateOrderFragment : BaseFragment<FragmentOrderRatingBinding>() {

    companion object {
        const val EXTRA_THERAPIST = "therapist"
        const val EXTRA_ID = "id"
    }

    private val vm: RateOrderVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_order_rating

    override fun initViews() {
        binding.btnSend.setOnClickListener {
            if (binding.ratingBar.rating < 1) {
                context?.toast("Mohon isi penilaian anda")
                return@setOnClickListener
            }
            if (binding.etComment.text.isNullOrBlank()) {
                context?.toast("Mohon isi komentar anda")
                return@setOnClickListener
            }

            vm.submitFeedback(binding.etComment.text.toString(), binding.ratingBar.rating.toInt())

        }
    }

    override fun initObservers() {
        vm.response.observe(viewLifecycleOwner) {

            if (it.status == Resource.Status.LOADING) {
                binding.loading.visible()
                binding.btnSend.disable()
            } else {
                binding.loading.gone()
                binding.btnSend.enable()

                if (it.status == Resource.Status.SUCCESS) {
                    findNavController().popBackStack()
                } else {
                    context?.toast(it.message.toString())
                }

            }


        }
    }

    override fun initData() {
        arguments?.getParcelable<Therapist>(EXTRA_THERAPIST)?.let {
            binding.tvName.text = it.name
            binding.imgFigure.loadRoundImage(R.drawable.pp)
        }
        arguments?.getString(EXTRA_ID)?.let { vm.setId(it) }
    }
}