package com.github.ahmadriza.mvvmboilerplate.ui.profile

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.ItemSettingBinding
import com.github.ahmadriza.mvvmboilerplate.models.MenuItem
import com.github.ahmadriza.mvvmboilerplate.utils.common.DataBoundListAdapter
import com.github.ahmadriza.mvvmboilerplate.utils.getBindingOf

class MenuAdapter :
    DataBoundListAdapter<MenuItem, ItemSettingBinding>(
        diffCallback = object : DiffUtil.ItemCallback<MenuItem>() {
            override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem == newItem
            }

        }
    ) {

    override fun createBinding(parent: ViewGroup): ItemSettingBinding {
        return parent.getBindingOf(R.layout.item_setting)
    }

    override fun bind(binding: ItemSettingBinding, item: MenuItem) {
        binding.tvTitle.text = item.title
        binding.root.setOnClickListener { item.onClick() }
    }


}