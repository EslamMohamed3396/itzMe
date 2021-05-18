package com.itzme.ui.fragment.contactProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.itzme.R
import com.itzme.data.models.contact.contactProfile.response.Data
import com.itzme.data.models.contact.contactProfile.response.MyLink
import com.itzme.databinding.FragmentContactProfileBinding
import com.itzme.ui.activity.main.ReadTagViewModel
import com.itzme.ui.base.BaseFragment
import com.itzme.ui.base.IClickOnItems
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.Resource
import com.itzme.utilits.SessionEnded


class ContactProfileFragment : BaseFragment<FragmentContactProfileBinding>(),
    IClickOnItems<MyLink> {

    private val myLinkAdapter: LinkProfileAdapter by lazy { LinkProfileAdapter(this) }
    private val viewModel: ContactProfileViewModel by viewModels()
    private val args: ContactProfileFragmentArgs by navArgs()
    private val viewModelReadTag: ReadTagViewModel by viewModels()

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
        if (args.userNameArgs != null) {
            initReadViewModel(args.userNameArgs!!)
        } else {
            initContactProfile(args.contactIdArgs)
        }
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

    //region init view model

    private fun initContactProfile(contactId: Int) {
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
                        showFindMeAndPetSheet(response.data.data.myLinks)
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


    private fun initReadViewModel(userName: String) {
        viewModelReadTag.readTag(userName, "").observe(viewLifecycleOwner, { response ->
            val message = response.data?.errorMessage
            when (response) {
                is Resource.Loading -> {
                    // DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    // DialogUtil.dismissDialog()
                    if (response.data?.errorCode == 0) {
                        initContactProfile(response.data.data!!)
                    } else {
                        val action =
                            ContactProfileFragmentDirections.actionContactProfileFragmentToMyProfileFragment()
                        findNavController().navigate(action)
                    }
                }
                is Resource.Error -> {
                    //  DialogUtil.dismissDialog()
                    when (response.code) {
                        401 -> {
                            SessionEnded.dialogSessionEnded(
                                requireActivity(),
                                findNavController(),
                                R.id.contactProfileFragment,
                                ContactProfileFragmentDirections.actionContactProfileFragmentToLoginFragment()
                            )
                        }
                        else -> {
                            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }
        })

    }

    private fun showFindMeAndPetSheet(item: List<MyLink>) {
        if (item.isNotEmpty()) {
            when (item[0].linkType) {
                42 -> {
                    val action =
                        ContactProfileFragmentDirections.actionContactProfileFragmentToPetSheetContact(
                            item[0]
                        )
                    findNavController().navigate(action)
                }
                43 -> {
                    val action =
                        ContactProfileFragmentDirections.actionContactProfileFragmentToFindMeSheetContact(
                            item[0]
                        )
                    findNavController().navigate(action)
                }
            }
        }
    }


    //endregion

    override fun clickOnItems(item: MyLink, postion: Int) {
        when (item.linkType) {
            in 0..41 -> {
                val action =
                    ContactProfileFragmentDirections.actionContactProfileFragmentToAddLinkSheetContact(
                        item
                    )
                findNavController().navigate(action)
            }
            42 -> {
                val action =
                    ContactProfileFragmentDirections.actionContactProfileFragmentToPetSheetContact(
                        item
                    )
                findNavController().navigate(action)
            }
            43 -> {
                val action =
                    ContactProfileFragmentDirections.actionContactProfileFragmentToFindMeSheetContact(
                        item
                    )
                findNavController().navigate(action)
            }
        }
    }
}