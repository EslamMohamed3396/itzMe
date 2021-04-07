package com.itzme.ui.fragment.contactProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.contact.contactProfile.response.MyLink
import com.itzme.databinding.ItemLinkProfileBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback

class LinkProfileAdapter :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val differ = AsyncListDiffer(this, DiffCallback<MyLink>())


    fun submitList(list: List<MyLink?>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemLinkProfileBinding: ItemLinkProfileBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_link_profile, parent, false
        )
        return MyLinkAdapterViewHolder(itemLinkProfileBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[position]
        (holder as MyLinkAdapterViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MyLinkAdapterViewHolder(val binding: ItemLinkProfileBinding) :
        BaseViewHolder<MyLink>(binding) {
        override fun bind(item: MyLink) {
            binding.myLink = item
            binding.executePendingBindings()


        }
    }
}