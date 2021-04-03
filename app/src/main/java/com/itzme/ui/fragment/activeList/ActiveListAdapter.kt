package com.itzme.ui.fragment.activeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.tags.tagType.response.Data
import com.itzme.databinding.ItemActiveBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback
import com.itzme.ui.base.IClickOnItems

class ActiveListAdapter(val iClickOnItems: IClickOnItems<Data>) :
        RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val differ = AsyncListDiffer(this, DiffCallback<Data>())


    fun submitList(list: List<Data?>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemActive: ItemActiveBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_active, parent, false
        )
        return ActiveListAdapterViewHolder(itemActive)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[position]
        (holder as ActiveListAdapterViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ActiveListAdapterViewHolder(val binding: ItemActiveBinding) :
            BaseViewHolder<Data>(binding) {
        override fun bind(item: Data) {
            binding.tagType = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                iClickOnItems.clickOnItems(item, adapterPosition)
            }

        }
    }
}