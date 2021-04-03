package com.itzme.ui.fragment.howToUse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.tags.howToUse.response.HowToUse
import com.itzme.databinding.HowToUseContainerBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback

class HowToUseAdapter :
        RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val differ = AsyncListDiffer(this, DiffCallback<HowToUse>())


    fun submitList(list: List<HowToUse?>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val howToUseContainerBinding: HowToUseContainerBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.how_to_use_container, parent, false
        )
        return HowToUseAdapterViewHolder(howToUseContainerBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[position]
        (holder as HowToUseAdapterViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class HowToUseAdapterViewHolder(val binding: HowToUseContainerBinding) :
            BaseViewHolder<HowToUse>(binding) {
        override fun bind(item: HowToUse) {
            binding.howToUse = item
            binding.executePendingBindings()


        }
    }
}