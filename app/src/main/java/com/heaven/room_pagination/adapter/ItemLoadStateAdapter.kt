package com.heaven.room_pagination.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.heaven.room_pagination.databinding.LoadStateItemBinding

class ItemLoadStateAdapter : LoadStateAdapter<ItemLoadStateAdapter.ItemLoadStateViewHolder>() {
    inner class ItemLoadStateViewHolder(val binding: LoadStateItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(
        holder: ItemLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.binding.apply {
            progress.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ItemLoadStateViewHolder {
        val binding =
            LoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemLoadStateViewHolder(binding)
    }
}