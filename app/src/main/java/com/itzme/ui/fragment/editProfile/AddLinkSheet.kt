package com.itzme.ui.fragment.editProfile

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.itzme.databinding.SheetSocialMediaBinding
import com.itzme.ui.SharedViewModel
import com.itzme.ui.fragment.editProfile.viewModel.EditLinkViewModel
import com.itzme.utilits.CheckValidData
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.Resource

class AddLinkSheet : BottomSheetDialogFragment() {

    lateinit var binding: SheetSocialMediaBinding
    private val args: AddLinkSheetArgs by navArgs()
    private val viewModel: EditLinkViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = SheetSocialMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        bindData()
        checkIfMore()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }


    //region bind data

    private fun checkIfMore() {
        when (args.addLinkArgs.linkType) {
            38, 39, 40, 41 -> {
                binding.changeNameInputLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun bindData() {
        binding.itemLink = args.addLinkArgs
    }

    //endregion

    //region init click
    private fun initClick() {
        binding.handle.setOnClickListener {
            dismiss()
        }
        binding.saveBtn.setOnClickListener {
            if (checkData()) {
                initEditLinkViewModel()
            }
        }
        binding.btnOpen.setOnClickListener {
            if (checkData()) {
                openNewTabWindow(binding.linkInputLayout.editText?.text.toString(), requireContext())
            }
        }
    }
    //endregion


    //region check Data
    private fun checkData(): Boolean {
        return CheckValidData.checkUrl(binding.linkInputLayout)
    }

    //endregion

    //region init view model

    private fun initEditLinkViewModel() {
        viewModel.updateLink(bodyEditLink()).observe(viewLifecycleOwner, { response ->
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

    private fun bodyEditLink(): BodyEditLink {
        return BodyEditLink(
                args.addLinkArgs.linkType,
                binding.changeNameInputLayout.editText?.text.toString(),
                binding.linkInputLayout.editText?.text.toString(),
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


    //region open link

    fun openNewTabWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }

    //endregion

}