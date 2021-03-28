package com.itzme.ui.fragment.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.contact.response.Data
import com.itzme.databinding.ItemContactBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback
import com.itzme.ui.base.IClickOnItems

class MyContactAdapter(val iClickOnItems: IClickOnItems<Data>) :
        RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val differ = AsyncListDiffer(this, DiffCallback<Data>())


    fun submitList(list: List<Data?>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemContactBinding: ItemContactBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_contact, parent, false
        )
        return MyContactAdapterViewHolder(itemContactBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[position]
        (holder as MyContactAdapterViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MyContactAdapterViewHolder(val binding: ItemContactBinding) :
            BaseViewHolder<Data>(binding) {
        override fun bind(item: Data) {
            binding.myContact = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                iClickOnItems.clickOnItems(item, adapterPosition)
            }

        }
    }
}