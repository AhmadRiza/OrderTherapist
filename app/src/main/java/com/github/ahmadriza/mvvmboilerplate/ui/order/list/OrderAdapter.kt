package com.github.ahmadriza.mvvmboilerplate.ui.order.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.ItemOrderBinding
import com.github.ahmadriza.mvvmboilerplate.models.Order
import com.github.ahmadriza.mvvmboilerplate.utils.common.DataBoundListAdapter
import com.github.ahmadriza.mvvmboilerplate.utils.formatCurrency
import com.github.ahmadriza.mvvmboilerplate.utils.getBindingOf
import com.github.ahmadriza.mvvmboilerplate.utils.gone
import com.github.ahmadriza.mvvmboilerplate.utils.loadRoundImage

class OrderAdapter(private val listener: Listener? = null) :
    DataBoundListAdapter<Order, ItemOrderBinding>(
        diffCallback = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem == newItem
            }

        }
    ) {
    override fun createBinding(parent: ViewGroup): ItemOrderBinding {
        return parent.getBindingOf(R.layout.item_order)
    }

    override fun bind(binding: ItemOrderBinding, item: Order) {
        binding.tvProductName.text = item.product.name
        binding.tvPrice.text = item.product.price.formatCurrency()
//        binding.tvTherapistName.text = item.therapist?.name
        binding.tvTherapistRate.gone()
        binding.imgTherapist.loadRoundImage(R.drawable.pp)
        binding.date.text = item.date
        binding.tvStatus.text = item.status
        binding.root.setOnClickListener { listener?.onOrderClicked(item) }
    }


    interface Listener {
        fun onOrderClicked(order: Order)
    }

}