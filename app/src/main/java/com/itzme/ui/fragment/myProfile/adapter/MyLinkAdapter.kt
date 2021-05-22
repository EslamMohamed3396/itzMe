package com.itzme.ui.fragment.myProfile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.profile.myProfile.response.MyLink
import com.itzme.databinding.ItemMyLinkBinding
import com.itzme.databinding.ItemMyLinkOffBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback
import com.itzme.utilits.Constant

class MyLinkAdapter(val iClickOnLink: IClickOnLink) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val differ = AsyncListDiffer(this, DiffCallback<MyLink>())
    private var isDirectOn: Boolean? = true
    private var itemTouchHelper: ItemTouchHelper? = null
    fun submitList(isDirectOn: Boolean?, list: List<MyLink?>?, itemTouchHelper: ItemTouchHelper?) {
        differ.submitList(list)
        this.isDirectOn = isDirectOn
        this.itemTouchHelper = itemTouchHelper
    }


    interface IClickOnLink {
        fun clickOnItems(item: MyLink, postion: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        when (viewType) {
            Constant.VIEW_MY_LINK_ON -> {
                val itemMyLinkBinding: ItemMyLinkBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_my_link, parent, false
                )
                return MyLinkAdapterViewHolder(itemMyLinkBinding)
            }
            else -> {
                val itemMyLinkOffBinding: ItemMyLinkOffBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_my_link_off, parent, false
                )
                return MyLinkOFFAdapterViewHolder(itemMyLinkOffBinding)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }



    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[holder.adapterPosition]

        when (holder.itemViewType) {
            Constant.VIEW_MY_LINK_ON -> {
                (holder as MyLinkAdapterViewHolder).bind(item)
            }
            else -> {
                (holder as MyLinkOFFAdapterViewHolder).bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            if (isDirectOn!!) {
                Constant.VIEW_MY_LINK_ON
            } else {
                Constant.VIEW_MY_LINK_ON
            }
        } else {
            if (isDirectOn!!) {
                Constant.VIEW_MY_LINK_OFF
            } else {
                Constant.VIEW_MY_LINK_ON
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class MyLinkAdapterViewHolder(val binding: ItemMyLinkBinding) :
        BaseViewHolder<MyLink>(binding) {
        override fun bind(item: MyLink) {
            binding.myLink = item
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                iClickOnLink.clickOnItems(item, adapterPosition)
            }
        }
    }

    inner class MyLinkOFFAdapterViewHolder(val binding: ItemMyLinkOffBinding) :
        BaseViewHolder<MyLink>(binding) {
        override fun bind(item: MyLink) {
            binding.myLink = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                iClickOnLink.clickOnItems(item, adapterPosition)
            }
        }
    }
}