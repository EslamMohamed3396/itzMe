package com.itzme.ui.fragment.myProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.profile.myProfile.response.MyLink
import com.itzme.databinding.ItemMyLinkBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback
import com.itzme.ui.base.IClickOnItems

class MyLinkAdapter :
        RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val differ = AsyncListDiffer(this, DiffCallback<MyLink>())


    fun submitList(list: List<MyLink?>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemMyLinkBinding:ItemMyLinkBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_my_link, parent, false
        )
        return MyLinkAdapterViewHolder(itemMyLinkBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[position]
        (holder as MyLinkAdapterViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MyLinkAdapterViewHolder(val binding: ItemMyLinkBinding) :
            BaseViewHolder<MyLink>(binding) {
        override fun bind(item: MyLink) {
            binding.myLink = item
            binding.executePendingBindings()


        }
    }
}