package com.github.ahmadriza.mvvmboilerplate.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.ItemMenuBinding
import com.github.ahmadriza.mvvmboilerplate.models.MenuItem
import com.github.ahmadriza.mvvmboilerplate.utils.common.DataBoundListAdapter

class MenuAdapter : DataBoundListAdapter<MenuItem, ItemMenuBinding>(
    diffCallback = object : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun createBinding(parent: ViewGroup): ItemMenuBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_menu,
            parent, false
        )
    }

    override fun bind(binding: ItemMenuBinding, item: MenuItem) {

        binding.imgIcon.setImageResource(item.icon)
        binding.tvTitle.text = item.title

        binding.root.setOnClickListener {
            item.onClick.invoke()
        }


    }
}