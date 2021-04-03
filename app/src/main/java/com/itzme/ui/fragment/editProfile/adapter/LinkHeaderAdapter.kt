package com.itzme.ui.fragment.editProfile.adapter

import android.view.LayoutInflater
import android.view.View
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
import net.cachapa.expandablelayout.ExpandableLayout
import timber.log.Timber


class LinkHeaderAdapter(val iClickOnItems: IClickOnItems<Link>) :
        RecyclerView.Adapter<BaseViewHolder<*>>(), IClickOnItems<Link> {
    private val differ = AsyncListDiffer(this, DiffCallback<AllLink>())
    private val UNSELECTED = -1

    private var selectedItem = UNSELECTED

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

        Timber.d("${singleSectionItems}")

        val itemListDataAdapter = LinkContentAdapter(this)

        holder.binding.recyclerViewList.setHasFixedSize(true)

        holder.binding.recyclerViewList.adapter = itemListDataAdapter
        itemListDataAdapter.submitList(singleSectionItems)

        // holder.bind()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class LinkHeaderAdapterViewHolder(val binding: ItemHeaderLinksBinding) :
            BaseViewHolder<AllLink>(binding), ExpandableLayout.OnExpansionUpdateListener,
            View.OnClickListener {
        override fun bind(item: AllLink) {
            binding.executePendingBindings()
            //   binding.cardView.setOnClickListener(this)
            //   binding.expandableLayout.setOnExpansionUpdateListener(this);
        }

        fun bind() {
            val position = adapterPosition
            val isSelected = position == selectedItem
//            binding.cardView.isSelected = isSelected
            binding.expandableLayout.setExpanded(isSelected, false)
        }

        override fun onClick(view: View?) {

            // binding.cardView.isSelected = false
            binding.expandableLayout.collapse()
            val position = adapterPosition
            selectedItem = if (position == selectedItem) {
                UNSELECTED
            } else {
                //  binding.cardView.isSelected = true
                binding.expandableLayout.expand()
                position
            }
        }

        override fun onExpansionUpdate(expansionFraction: Float, state: Int) {
            if (state == ExpandableLayout.State.EXPANDING) {
                binding.recyclerViewList.smoothScrollToPosition(adapterPosition)
            }
        }
    }

    override fun clickOnItems(item: Link, postion: Int) {
        iClickOnItems.clickOnItems(item, postion)
    }
}