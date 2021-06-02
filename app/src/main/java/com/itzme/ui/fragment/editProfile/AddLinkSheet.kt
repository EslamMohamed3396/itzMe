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
import com.itzme.utilits.ClickOnLink
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
            in 38..41 -> {
                binding.changeNameInputLayout.visibility = View.VISIBLE
            }
        }
    }

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
            when (args.addLinkArgs.linkType) {
                21 -> {
                    if (checkName()) {
                        initEditLinkViewModel(binding.linkInputLayout.editText?.text.toString(), binding.changeNameInputLayout.editText?.text.toString())
                    }
                }
                11, 12 -> {
                    if (checkEmail()) {
                        initEditLinkViewModel(binding.linkInputLayout.editText?.text.toString(), binding.changeNameInputLayout.editText?.text.toString())
                    }
                }
                0, 1, 7, 9, 23, 26, in 30..41 -> {
                    if (checkData()) {
                        initEditLinkViewModel(binding.linkInputLayout.editText?.text.toString(), binding.changeNameInputLayout.editText?.text.toString())
                    }
                }
                else -> {
                    if (checkName()) {
                        initEditLinkViewModel(binding.linkInputLayout.editText?.text.toString(), binding.changeNameInputLayout.editText?.text.toString())
                    }
                }
            }
        }
        binding.removeBtn.setOnClickListener {
            if (checkBeforeRemove()) {
                initEditLinkViewModel("", "")
            }
        }
        binding.btnOpen.setOnClickListener {
            composeEmail(binding.linkInputLayout.editText?.text.toString())
//            if (checkData()) {
//                openNewTabWindow(binding.linkInputLayout.editText?.text.toString(), requireContext())
//            }
        }
    }
    //endregion


    //region check Data
    private fun checkData(): Boolean {
        return CheckValidData.checkUrl(binding.linkInputLayout)
    }

    private fun checkName(): Boolean {
        return binding.linkInputLayout.editText?.text.toString().isNotEmpty()
    }

    private fun checkBeforeRemove(): Boolean {
        return binding.linkInputLayout.editText?.text.toString().isNotEmpty()
    }

    private fun checkEmail(): Boolean {
        return CheckValidData.checkEmail(binding.linkInputLayout)
    }

    //endregion

    //region init view model

    private fun initEditLinkViewModel(link: String, changeName: String) {
        viewModel.updateLink(bodyEditLink(link, changeName)).observe(viewLifecycleOwner, { response ->
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

    private fun bodyEditLink(link: String, changeName: String): BodyEditLink {
        return BodyEditLink(
                args.addLinkArgs.linkType,
                changeName,
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


    //region open link

    fun openNewTabWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        if (intents.resolveActivity(requireContext().packageManager) != null) {
            requireContext().startActivity(intents)
        }
    }


    private fun composeEmail(email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        startActivity(Intent.createChooser(emailIntent, "Chooser Title"))
    }


    //endregion

}