package com.itzme.ui.fragment.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.itzme.R
import com.itzme.data.models.contact.response.Data
import com.itzme.databinding.ItemContactBinding
import com.itzme.ui.base.BaseViewHolder
import com.itzme.ui.base.DiffCallback
import com.itzme.ui.base.IClickOnItems
import java.util.*
import kotlin.collections.ArrayList

class MyContactAdapter(val iClickOnItems: IClickOnItems<Data>) :
        RecyclerView.Adapter<BaseViewHolder<*>>(), Filterable {
    private val differ = AsyncListDiffer(this, DiffCallback<Data>())
    private var contactListSearch = mutableListOf<Data>()
    var contactList: ArrayList<Data> = ArrayList()


    private val companiesFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Data> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(contactListSearch)
            } else {
                val filterPattern =
                        constraint.toString().toLowerCase(Locale.ROOT)

                for (contact in contactListSearch) {
                    if (contact.name?.toLowerCase(Locale.ROOT)
                                    ?.contains(filterPattern) == true
                    ) {
                        filteredList.add(contact)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(
                constraint: CharSequence,
                results: FilterResults
        ) {
//            if (faqList != null) {
            contactList.clear()
            contactList.addAll(results.values as MutableList<Data>)
//            }
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return companiesFilter
    }


    fun submitList(list: List<Data?>?) {
        differ.submitList(list)
        this.contactList = list as ArrayList<Data>
        contactListSearch = ArrayList(list)
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
            binding.imageView7.setOnClickListener {
                iClickOnItems.clickOnItems(item, -1)
            }
            binding.root.setOnClickListener {
                iClickOnItems.clickOnItems(item, adapterPosition)
            }

        }
    }
}