package com.github.ahmadriza.mvvmboilerplate.ui.topup

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.ItemTopUpBinding
import com.github.ahmadriza.mvvmboilerplate.models.Invoice
import com.github.ahmadriza.mvvmboilerplate.utils.DOT
import com.github.ahmadriza.mvvmboilerplate.utils.common.DataBoundListAdapter
import com.github.ahmadriza.mvvmboilerplate.utils.displayDate
import com.github.ahmadriza.mvvmboilerplate.utils.formatCurrency
import com.github.ahmadriza.mvvmboilerplate.utils.getBindingOf

class InvoiceAdapter(private val listener: Listener? = null) :
    DataBoundListAdapter<Invoice, ItemTopUpBinding>(
        diffCallback = object : DiffUtil.ItemCallback<Invoice>() {
            override fun areItemsTheSame(oldItem: Invoice, newItem: Invoice): Boolean {
                return oldItem.code == newItem.code
            }

            override fun areContentsTheSame(oldItem: Invoice, newItem: Invoice): Boolean {
                return oldItem == newItem
            }

        }
    ) {

    override fun createBinding(parent: ViewGroup): ItemTopUpBinding {
        return parent.getBindingOf(R.layout.item_top_up)
    }

    override fun bind(binding: ItemTopUpBinding, item: Invoice) {
        binding.tvNominal.text = item.finalAmount.formatCurrency()
        binding.tvDate.text = "${item.status} $DOT ${item.date.displayDate(binding.root.context)}"
        binding.root.setOnClickListener { listener?.onInvoiceClick(item) }
    }

    interface Listener {
        fun onInvoiceClick(Invoice: Invoice)
    }

}