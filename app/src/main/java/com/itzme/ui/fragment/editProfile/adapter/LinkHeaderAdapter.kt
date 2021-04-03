package com.itzme.ui.fragment.editProfile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.profile.myProfile.response.AllLink
import com.itzme.data.models.profile.myProfile.response.Link
import com.itzme.databinding.ItemHeaderLinksBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback
import com.itzme.ui.base.IClickOnItems
import timber.log.Timber


class LinkHeaderAdapter(val iClickOnItems: IClickOnItems<Link>) :
        RecyclerView.Adapter<BaseViewHolder<*>>(), IClickOnItems<Link> {
    private val differ = AsyncListDiffer(this, DiffCallback<AllLink>())
    private var isCollapse = false


    fun submitList(list: List<AllLink?>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemHeaderLink: ItemHeaderLinksBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_header_links, parent, false
        )
        return LinkHeaderAdapterViewHolder(itemHeaderLink)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = differ.currentList[position]
        val sectionName: String = item.name!!

        val singleSectionItems: List<Link> = item.links!!

        (holder as LinkHeaderAdapterViewHolder).binding.itemTitle.text = sectionName

        holder.bind(item)

        val itemListDataAdapter = LinkContentAdapter(this)

        holder.binding.recyclerViewList.setHasFixedSize(true)

        holder.binding.recyclerViewList.adapter = itemListDataAdapter
        itemListDataAdapter.submitList(singleSectionItems)


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class LinkHeaderAdapterViewHolder(val binding: ItemHeaderLinksBinding) :
            BaseViewHolder<AllLink>(binding) {
        override fun bind(item: AllLink) {
            binding.executePendingBindings()
            binding.linearLayout2.setOnClickListener {
                Timber.d("OnClick")
                isCollapse = if (isCollapse) {
                    binding.imOpenClose.setImageResource(R.drawable.left_arrow)
                    binding.expandableLayout.collapse()
                    false
                } else {
                    binding.imOpenClose.setImageResource(R.drawable.down_arrow)
                    binding.expandableLayout.expand()
                    true
                }
            }
        }

    }

    override fun clickOnItems(item: Link, postion: Int) {
        iClickOnItems.clickOnItems(item, postion)
    }
}