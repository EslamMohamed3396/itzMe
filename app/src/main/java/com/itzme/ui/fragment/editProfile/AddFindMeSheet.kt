package com.itzme.ui.fragment.editProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itzme.R
import com.itzme.data.models.profile.editLink.request.BodyEditLink
import com.itzme.databinding.SheetIfYouFindBinding
import com.itzme.ui.SharedViewModel
import com.itzme.ui.fragment.editProfile.viewModel.EditLinkViewModel
import com.itzme.utilits.CheckValidData
import com.itzme.utilits.Constant
import com.itzme.utilits.ImageUtil
import com.itzme.utilits.Resource
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog

class AddFindMeSheet : BottomSheetDialogFragment() {

    lateinit var binding: SheetIfYouFindBinding
    private val args: AddFindMeSheetArgs by navArgs()
    private val viewModel: EditLinkViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var imageBitmap: String? = null
    private var isActive: Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = SheetIfYouFindBinding.inflate(inflater, container, false)
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

    //region convert image from url to bitmap encoding
    private fun convertImage(urlImage: String?) {
        imageBitmap = ImageUtil.encodeImage(ImageUtil.getBitmapFromURL(urlImage))
    }

    //endregion


    //region pick and capture image
    private fun pickImage() {
        PickImageDialog.build(PickSetup())
                .setOnPickResult {
                    if (it.error == null) {
                        imageBitmap = ImageUtil.encodeImage(it.bitmap)!!
                        binding.imProfile.setImageBitmap(it.bitmap)
                        binding.btnImage.visibility = View.GONE
                    } else {
                        Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                    }
                }.setOnPickCancel {
                }.show(childFragmentManager)
    }

    //endregion

    //region bind data

    private fun bindData() {
        binding.findMeData = args.findMeArgs
        convertImage(args.findMeArgs.imageUrl)
        isActive = args.findMeArgs.findMeData.isActive!!

    }

    //endregion

    //region init click
    private fun initClick() {
        binding.handle.setOnClickListener {
            dismiss()
        }
        binding.imProfile.setOnClickListener {
            pickImage()
        }
        binding.btnImage.setOnClickListener {
            pickImage()
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
        return CheckValidData.checkName(binding.nameInputLayout) &&
                CheckValidData.checkName(binding.addressInputLayout) &&
                CheckValidData.checkName(binding.bloodTypeInputLayout) &&
                CheckValidData.checkName(binding.informationInputLayout) &&
                CheckValidData.checkName(binding.emNameInputLayout) &&
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
                Constant.LINK_FIND_ME,
                binding.nameInputLayout.editText?.text.toString(),
                null,
                0,
                isActive,
                binding.addressInputLayout.editText?.text.toString(),
                binding.informationInputLayout.editText?.text.toString(),
                binding.bloodTypeInputLayout.editText?.text.toString(),
                binding.emNameInputLayout.editText?.text.toString(),
                binding.emPhoneInputLayout.editText?.text.toString(),
                null,
                imageBitmap
        )
    }

    //endregion


}