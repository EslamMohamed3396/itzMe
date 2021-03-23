package com.itzme.ui.base

import androidx.recyclerview.widget.DiffUtil

class DiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return oldItem?.equals(newItem)!!
    }
}