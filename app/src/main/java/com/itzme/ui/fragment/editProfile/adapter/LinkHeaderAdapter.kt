package com.itzme.ui.fragment.editProfile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
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


class LinkHeaderAdapter(
    val iClickOnItems: IClickOnItems<Link>,
    val iOpenCloseExpandRecycler: IOpenCloseExpandRecycler
) :
    RecyclerView.Adapter<BaseViewHolder<*>>(), IClickOnItems<Link> {
    private val differ = AsyncListDiffer(this, DiffCallback<AllLink>())
    private var position: Int? = null

    fun submitList(list: List<AllLink?>?) {
        differ.submitList(list)
    }

    fun submitPostion(position: Int?) {
        this.position = position
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


    interface IOpenCloseExpandRecycler {
        fun openCloseExpandRecycler(
            position: Int,
            imageView: ImageView,
            expandableLayout: ExpandableLayout
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class LinkHeaderAdapterViewHolder(val binding: ItemHeaderLinksBinding) :
        BaseViewHolder<AllLink>(binding) {
        override fun bind(item: AllLink) {
            binding.executePendingBindings()
            expandLayout(binding)
            binding.linearLayout2.setOnClickListener {
                iOpenCloseExpandRecycler.openCloseExpandRecycler(
                    adapterPosition,
                    binding.imOpenClose,
                    binding.expandableLayout
                )
            }
        }

    }

    private fun expandLayout(binding: ItemHeaderLinksBinding) {
        if (position == null) {
            binding.imOpenClose.setImageResource(R.drawable.left_arrow)
            binding.expandableLayout.collapse()
        } else {
            binding.imOpenClose.setImageResource(R.drawable.down_arrow)
            binding.expandableLayout.expand()
        }
    }

    override fun clickOnItems(item: Link, postion: Int) {
        iClickOnItems.clickOnItems(item, postion)
    }
}