package com.github.ahmadriza.mvvmboilerplate.ui.order.rate

import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentOrderRatingBinding
import com.github.ahmadriza.mvvmboilerplate.models.Therapist
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.loadRoundImage

class RateOrderFragment : BaseFragment<FragmentOrderRatingBinding>() {

    companion object {
        const val EXTRA_THERAPIST = "therapist"
    }

    override fun getLayoutResource(): Int = R.layout.fragment_order_rating

    override fun initViews() {

    }

    override fun initObservers() {

    }

    override fun initData() {
        arguments?.getParcelable<Therapist>(EXTRA_THERAPIST)?.let {
            binding.tvName.text = it.name
            binding.imgFigure.loadRoundImage(R.drawable.pp)
        }
    }
}