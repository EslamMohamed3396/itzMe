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
import com.itzme.databinding.SheetPetBinding
import com.itzme.ui.SharedViewModel
import com.itzme.ui.fragment.editProfile.viewModel.EditLinkViewModel
import com.itzme.utilits.CheckValidData
import com.itzme.utilits.Constant
import com.itzme.utilits.Resource

class AddPetSheet : BottomSheetDialogFragment() {

    lateinit var binding: SheetPetBinding
    private val args: AddPetSheetArgs by navArgs()
    private val viewModel: EditLinkViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var isActive: Boolean = false
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = SheetPetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        bindData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }


    //region bind data

    private fun bindData() {
        binding.petData = args.petArgs
        isActive = args.petArgs?.isActive!!
    }

    //endregion

    //region init click
    private fun initClick() {
        binding.handle.setOnClickListener {
            dismiss()
        }
        binding.saveBtn.setOnClickListener {
            if (checkData()) {
                initEditLinkViewModel(1)
            }
        }

        binding.btnActive.setOnClickListener {
            if (isActive) {
                initEditLinkViewModel(0)
            } else {
                initEditLinkViewModel(1)
            }
        }
    }
    //endregion


    //region check Data
    private fun checkData(): Boolean {
        return CheckValidData.checkEditText(binding.petTypeInputLayout) &&
                CheckValidData.checkEditText(binding.petNameInputLayout) &&
                CheckValidData.checkEditText(binding.petInformationInputLayout) &&
                CheckValidData.checkEditText(binding.emNameInputLayout) &&
                CheckValidData.checkPhone(binding.emPhoneInputLayout)
    }

    //endregion




    //region init view model

    private fun initEditLinkViewModel(isActive: Int) {
        viewModel.updateLink(bodyEditLink(isActive)).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    // DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    //  DialogUtil.dismissDialog()
                    findNavController().navigateUp()
                    sharedViewModel.saveDismissed(true)
                }
                is Resource.Error -> {
                    // DialogUtil.dismissDialog()
                    when (response.code) {
                        13, 14 -> {

                        }
                    }
                }

            }
        })

    }

    private fun bodyEditLink(isActive: Int): BodyEditLink {
        return BodyEditLink(
                Constant.LINK_PET,
                binding.petNameInputLayout.editText?.text.toString(),
                null,
                0,
                isActive,
                null,
                binding.petInformationInputLayout.editText?.text.toString(),
                null,
                binding.emNameInputLayout.editText?.text.toString(),
                binding.emPhoneInputLayout.editText?.text.toString(),
                binding.petTypeInputLayout.editText?.text.toString(),
                null
        )
    }


    //endregion


}