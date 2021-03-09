package com.github.ahmadriza.mvvmboilerplate.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.ItemProductBinding
import com.github.ahmadriza.mvvmboilerplate.models.Product
import com.github.ahmadriza.mvvmboilerplate.utils.common.DataBoundListAdapter
import com.github.ahmadriza.mvvmboilerplate.utils.formatCurrency
import com.github.ahmadriza.mvvmboilerplate.utils.getBindingOf
import com.github.ahmadriza.mvvmboilerplate.utils.loadImage

class ProductAdapter(private val listener: Listener? = null) :
    DataBoundListAdapter<Product, ItemProductBinding>(
        diffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }
    ) {

    override fun createBinding(parent: ViewGroup): ItemProductBinding {
        return parent.getBindingOf(R.layout.item_product)
    }

    override fun bind(binding: ItemProductBinding, item: Product) {
        binding.tvName.text = item.name
        binding.tvDesc.text = item.description
        binding.tvPrice.text = "${item.price.formatCurrency()}/${item.duration} menit"
        binding.btnOrder.setOnClickListener { listener?.onProductOrder(item) }
        binding.imgProduct.loadImage(item.thumbnail)
    }


    interface Listener {
        fun onProductOrder(product: Product)
    }

}