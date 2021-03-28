package com.itzme.ui.fragment.myProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.itemMenu.ItemMenu
import com.itzme.databinding.ItemMenuListBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback
import com.itzme.ui.base.IClickOnItems

class ItemMenuAdapter(val iClickOnItems: IClickOnItems<ItemMenu>) :
        RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val differ = AsyncListDiffer(this, DiffCallback<ItemMenu>())


    fun submitList(list: List<ItemMenu?>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemMenuListBinding: ItemMenuListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_menu_list, parent, false
        )
        return MoreAdapterViewHolder(itemMenuListBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[position]
        (holder as MoreAdapterViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MoreAdapterViewHolder(val binding: ItemMenuListBinding) :
            BaseViewHolder<ItemMenu>(binding) {
        override fun bind(item: ItemMenu) {
            binding.itemMenu = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                iClickOnItems.clickOnItems(item, adapterPosition)
            }

        }
    }
}