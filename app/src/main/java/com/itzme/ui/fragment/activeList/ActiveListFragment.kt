package com.itzme.ui.fragment.activeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.data.models.tags.tagType.response.Data
import com.itzme.databinding.FragmentActiveListBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.ui.base.IClickOnItems
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.Resource

class ActiveListFragment : BaseFragment<FragmentActiveListBinding>(), IClickOnItems<Data> {


    private val activeListAdapter: ActiveListAdapter by lazy { ActiveListAdapter(this) }
    private lateinit var viewModel: ActiveListViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_active_list)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.toolbar?.titleTv?.text = requireContext().resources.getString(R.string.activate)
        initClick()
        initActiveViewModel()
    }

    //region init click
    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    //endregion

    //region bind data
    private fun bindAdapter() {
        binding?.activeListAdapter = activeListAdapter
    }

    //endregion

    //region init view Model

    private fun initActiveViewModel() {
        bindAdapter()
        viewModel = ViewModelProvider(this).get(ActiveListViewModel::class.java)
        viewModel.tagType().observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    if (response.data?.data?.isNotEmpty()!!) {
                        activeListAdapter.submitList(response.data.data)
                    } else {
                        binding?.tvEmpty?.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        401 -> {

                        }
                    }
                }
            }
        })
    }


    //endregion

    override fun clickOnItems(item: Data, postion: Int) {
        val action =
                ActiveListFragmentDirections.actionActiveListFragmentToActiveProductFragment()
        findNavController().navigate(action)
    }


}