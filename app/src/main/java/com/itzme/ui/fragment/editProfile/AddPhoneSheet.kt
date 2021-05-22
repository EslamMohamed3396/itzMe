package com.itzme.ui.fragment.editProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itzme.R
import com.itzme.data.models.profile.editLink.request.BodyEditLink
import com.itzme.databinding.SheetSocialPhoneBinding
import com.itzme.ui.SharedViewModel
import com.itzme.ui.fragment.editProfile.viewModel.EditLinkViewModel
import com.itzme.utilits.CheckValidData
import com.itzme.utilits.ClickOnLink
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.Resource
import timber.log.Timber

class AddPhoneSheet : BottomSheetDialogFragment() {

    lateinit var binding: SheetSocialPhoneBinding
    private val args: AddLinkSheetArgs by navArgs()
    private val viewModel: EditLinkViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SheetSocialPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        bindData()
        //removeCountryCode()

        //goneCountryCode()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }


    //region  view gone to coutry code
    private fun goneCountryCode() {
        if (checkIfLinkType()) {
            binding.countryCode.visibility = View.GONE
        }
    }

    //endregion


    //region bind data


    private fun bindData() {
        val clickOnLink = ClickOnLink()
        binding.clickLink = clickOnLink
        binding.itemLink = args.addLinkArgs
    }

    //endregion

    //region init click
    private fun initClick() {
        binding.handle.setOnClickListener {
            dismiss()
        }
        binding.saveBtn.setOnClickListener {
            if (checkIfLinkType()) {
                if (checkBeforeRemove()) {
                    initEditLinkViewModel(binding.linkInputLayout.editText?.text.toString())
                }
            } else {
                if (checkData()) {
                    initEditLinkViewModel(binding.linkInputLayout.editText?.text.toString())
                }
            }

        }
        binding.removeBtn.setOnClickListener {
            if (checkBeforeRemove()) {
                initEditLinkViewModel("")
            }
        }

    }
    //endregion


    //region remove country code

    private fun removeCountryCode() {

        binding.countryCode.registerCarrierNumberEditText(binding.linkInputLayout.editText)
        val phone = args.addLinkArgs.link?.
        split(binding.countryCode.selectedCountryCode)
        Timber.d("${phone}")
    }


    //endregion


    //region check Data
    private fun checkData(): Boolean {
        return CheckValidData.checkPhone(binding.countryCode, binding.linkInputLayout)
    }

    private fun checkIfLinkType(): Boolean {
        return when (args.addLinkArgs.linkType) {
            13, 18, 19, 20 -> {
                true
            }
            else -> {
                false
            }
        }
    }


    private fun checkBeforeRemove(): Boolean {
        return binding.linkInputLayout.editText?.text.toString().isNotEmpty()
    }
    //endregion

    //region init view model

    private fun initEditLinkViewModel(link: String) {
        viewModel.updateLink(bodyEditLink(link)).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    findNavController().navigateUp()
                    sharedViewModel.saveDismissed(true)
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

    private fun bodyEditLink(link: String): BodyEditLink {
        return BodyEditLink(
            args.addLinkArgs.linkType,
            null,
            link,
            0,
            0,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

    //endregion

}