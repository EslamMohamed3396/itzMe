package com.itzme.ui.fragment.editProfile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.profile.myProfile.response.Link
import com.itzme.databinding.ItemAllLinkBinding
import com.itzme.databinding.ItemLinkBinding
import com.itzme.databinding.ItemMyLinkBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback
import com.itzme.ui.base.IClickOnItems
import com.itzme.utilits.Constant

class LinkContentAdapter(val iClickOnItems: IClickOnItems<Link>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val differ = AsyncListDiffer(this, DiffCallback<Link>())


    fun submitList(list: List<Link?>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        when (viewType) {
            Constant.VIEW_MY_LINK_ON -> {
                val itemAllLinkBinding: ItemAllLinkBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_all_link, parent, false
                )
                return MyLinkONAdapterViewHolder(itemAllLinkBinding)
            }
            else -> {
                val itemLinkBinding: ItemLinkBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_link, parent, false
                )
                return LinkAdapterViewHolder(itemLinkBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[position]
        when (holder.itemViewType) {
            Constant.VIEW_MY_LINK_ON -> {
                (holder as MyLinkONAdapterViewHolder).bind(item)
            }
            else -> {
                (holder as LinkAdapterViewHolder).bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun getItemViewType(position: Int): Int {
        val item = differ.currentList[position]
        return if (!item.link.isNullOrEmpty()) {
            Constant.VIEW_MY_LINK_ON
        } else {
            Constant.VIEW_MY_LINK_OFF
        }

    }


    inner class MyLinkONAdapterViewHolder(val binding: ItemAllLinkBinding) :
        BaseViewHolder<Link>(binding) {
        override fun bind(item: Link) {
            binding.myLink = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                iClickOnItems.clickOnItems(item, adapterPosition)
            }
        }
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