package com.itzme.ui.fragment.editProfile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.profile.myProfile.response.Link
import com.itzme.databinding.ItemLinkBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback
import com.itzme.ui.base.IClickOnItems

class LinkContentAdapter(val iClickOnItems: IClickOnItems<Link>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val differ = AsyncListDiffer(this, DiffCallback<Link>())


    fun submitList(list: List<Link?>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemLinkBinding: ItemLinkBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_link, parent, false
        )
        return LinkAdapterViewHolder(itemLinkBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[position]
        (holder as LinkAdapterViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class LinkAdapterViewHolder(val binding: ItemLinkBinding) :
            BaseViewHolder<Link>(binding) {
        override fun bind(item: Link) {
            binding.itemLink = item
            binding.executePendingBindings()
            binding.materialCardView2.setOnClickListener {
                iClickOnItems.clickOnItems(item, adapterPosition)
            }
        }
    }
}