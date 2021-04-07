package com.itzme.ui.fragment.contactProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.itzme.R
import com.itzme.data.models.contact.contactProfile.response.Data
import com.itzme.databinding.FragmentContactProfileBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.Resource

class ContactProfileFragment : BaseFragment<FragmentContactProfileBinding>() {

    private val myLinkAdapter: LinkProfileAdapter by lazy { LinkProfileAdapter() }
    private val viewModel: ContactProfileViewModel by viewModels()
    private val args: ContactProfileFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_contact_profile)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.toolbar?.titleTv?.text = requireContext().resources.getString(R.string.profile)
        initClick()
        initMyProfileViewModel(args.contactIdArgs)
    }


    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    //region bindData

    private fun bindAdapter() {
        binding?.link = myLinkAdapter
    }

    private fun bindMyProfile(myProfile: Data) {
        binding?.myProfile = myProfile
    }

    //endregion

    private fun initMyProfileViewModel(contactId: Int) {
        bindAdapter()
        viewModel.contactProfile(contactId).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {

                    DialogUtil.dismissDialog()

                    bindMyProfile(response.data?.data!!)
                    if (response.data.data.myLinks?.isNotEmpty()!!) {
                        myLinkAdapter.submitList(response.data.data.myLinks)
                    } else {
                        binding?.tvEmpty?.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()

                    when (response.code) {
                        13, 14 -> {

                        }
                    }
                }

            }
        })

    }


}