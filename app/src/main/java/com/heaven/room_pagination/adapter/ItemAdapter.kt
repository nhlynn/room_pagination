package com.heaven.room_pagination.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.heaven.room_pagination.databinding.ItemBinding
import com.heaven.room_pagination.persistence.table.ItemVO

class ItemAdapter : PagingDataAdapter<ItemVO, ItemAdapter.ItemViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemVO>() {
            override fun areItemsTheSame(oldItem: ItemVO, newItem: ItemVO): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ItemVO, newItem: ItemVO): Boolean =
                oldItem == newItem
        }
    }

    class ItemViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            tvItem.text = item?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }
}