package com.itzme.ui.fragment.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.data.models.contact.response.Data
import com.itzme.databinding.FragmentContactBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.ui.base.IClickOnItems
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.Resource
import timber.log.Timber

class ContactFragment : BaseFragment<FragmentContactBinding>(), IClickOnItems<Data>, MyContactAdapter.ClickOnDeleteContact {


    private val myContactAdapter: MyContactAdapter by lazy { MyContactAdapter(this, this) }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_contact)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.toolbar?.titleTv?.text = requireContext().resources.getString(R.string.contacts)
        initMyContactViewModel()
        initSearch()
        initClick()
    }

    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initAdapter() {
        binding?.contactAdapter = myContactAdapter
    }

    private fun initSearch() {
        binding?.searchInputLayoutLogin?.editText?.doOnTextChanged { text, _, _, _ ->
            myContactAdapter.filter.filter(text)
        }

    }


    private fun initMyContactViewModel() {
        initAdapter()
        val viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        viewModel.myContact().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    if (!response.data?.data?.isEmpty()!!) {
                        myContactAdapter.submitList(response.data.data)
                    } else {
                        binding?.tvEmpty?.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    Timber.d("${response.code}")

                    when (response.code) {
                        401 -> {

                        }
                    }
                }

            }
        })
    }

    private fun initDeleteContactViewModel(contactId: Int) {
        val viewModel = ViewModelProvider(this).get(DeleteContactViewModel::class.java)
        viewModel.deleteContact(contactId).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    initMyContactViewModel()
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    Timber.d("${response.code}")

                    when (response.code) {
                        401 -> {

                        }
                        else -> {
                            Toast.makeText(requireContext(), requireContext().resources.getString(R.string.error), Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        })
    }

    override fun clickOnItems(item: Data, postion: Int) {

    }

    override fun clickOnDelete(item: Data) {
        initDeleteContactViewModel(item.id!!)
    }

}