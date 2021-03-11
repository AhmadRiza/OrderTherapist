package com.github.ahmadriza.mvvmboilerplate.ui.order.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.ItemOrderBinding
import com.github.ahmadriza.mvvmboilerplate.models.Order
import com.github.ahmadriza.mvvmboilerplate.models.OrderStatus
import com.github.ahmadriza.mvvmboilerplate.utils.*
import com.github.ahmadriza.mvvmboilerplate.utils.common.DataBoundListAdapter

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
        binding.tvTherapistName.text = item.therapist?.name
        binding.tvTherapistRate.gone()
        binding.imgTherapist.loadRoundImage(item.therapist?.avatar)
        binding.date.text = item.date
        binding.tvStatus.text = statusData[item.status]?.first
        binding.tvStatus.setTextColor(
            binding.root.context.getCompatColor(
                statusData[item.status]?.second ?: R.color.textPrimary
            )
        )


        binding.root.setOnClickListener { listener?.onOrderClicked(item) }
    }


    interface Listener {
        fun onOrderClicked(order: Order)
    }

    private val statusData = mapOf(
        OrderStatus.pending to Pair("Menunggu Konfirmasi", R.color.yellow),
        OrderStatus.canceled to Pair("Pesanan Dibatalkan", R.color.customPrimary),
        OrderStatus.success to Pair("Pesanan Selesai", R.color.colorAccent),
        OrderStatus.process to Pair("Terapis Menuju Lokasi", R.color.yellow)
    )

}