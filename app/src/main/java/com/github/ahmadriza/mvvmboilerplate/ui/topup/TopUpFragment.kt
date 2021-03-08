package com.github.ahmadriza.mvvmboilerplate.ui.topup

import androidx.fragment.app.activityViewModels
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentTopUpBinding
import com.github.ahmadriza.mvvmboilerplate.models.Invoice
import com.github.ahmadriza.mvvmboilerplate.utils.attachCurrencyFormatter
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.clearCurrency
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.visibleOrGone
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class TopUpFragment : BaseFragment<FragmentTopUpBinding>(), InvoiceAdapter.Listener {

    private val vm: TopUpVM by activityViewModels()
    private val adapter by lazy { InvoiceAdapter(this) }

    override fun getLayoutResource(): Int = R.layout.fragment_top_up

    override fun initViews() {
        binding.rvHistory.adapter = adapter
        binding.btnTopUp.setOnClickListener {

            val nominal = binding.etNominal.text.toString().clearCurrency()
            if (nominal <= 20000) {
                context?.toast("Nominal minimal Rp20.000")
                return@setOnClickListener
            }
            vm.submitTopUp(nominal)

        }
        binding.etNominal.attachCurrencyFormatter()
        binding.swipe.setOnRefreshListener { vm.refreshHistory() }
    }

    override fun initObservers() {
        vm.response.observe(viewLifecycleOwner) {
            binding.loading.visibleOrGone(it.status == Resource.Status.LOADING)
            binding.btnTopUp.isEnabled = it.status != Resource.Status.LOADING
            if (it.status == Resource.Status.SUCCESS) it.data?.data?.let { invoice ->
                context?.toast(invoice.toString())
                vm.refreshHistory()
            } else {
                context?.toast(it.message.toString())
            }
        }
        vm.history.observe(viewLifecycleOwner) {
            binding.swipe.isRefreshing = it.status == Resource.Status.LOADING
            if (it.status == Resource.Status.SUCCESS) it.data?.data?.let {
                adapter.submitList(
                    listOf(
                        it
                    )
                )
            }
            else adapter.currentList.clear()
        }
    }

    override fun initData() {

    }

    override fun onInvoiceClick(Invoice: Invoice) {

    }

}