package com.itzme.ui.fragment.editProfile

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.itzme.R
import com.itzme.data.models.profile.editLink.request.BodyEditLink
import com.itzme.data.models.profile.editProfile.request.BodyEditProfile
import com.itzme.data.models.profile.myProfile.response.Link
import com.itzme.data.models.profile.myProfile.response.MyLink
import com.itzme.data.models.profile.myProfile.response.PetData
import com.itzme.data.models.profile.myProfile.response.ResponseMyProfile
import com.itzme.databinding.FragmentEditProfileBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.ui.base.IClickOnItems
import com.itzme.ui.fragment.editProfile.adapter.LinkHeaderAdapter
import com.itzme.ui.fragment.editProfile.viewModel.EditLinkViewModel
import com.itzme.ui.fragment.editProfile.viewModel.EditProfileViewModel
import com.itzme.ui.fragment.myProfile.adapter.MyLinkAdapter
import com.itzme.ui.fragment.myProfile.viewModels.MyProfileViewModel
import com.itzme.ui.fragment.myProfile.viewModels.TurnOnOffProfileViewModel
import com.itzme.utilits.*
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import timber.log.Timber


class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>(), IClickOnItems<Link>, MyLinkAdapter.IClickOnLink {

    private val viewModel: EditProfileViewModel by viewModels()
    private val viewModelEditLink: EditLinkViewModel by viewModels()
    private val viewModelTurnOnOff: TurnOnOffProfileViewModel by viewModels()

    private var imageBitmap: String? = null
    private var isProfilePrivate: Boolean = true
    private val myLinkAdapter: MyLinkAdapter by lazy { MyLinkAdapter(this) }
    private val allLinkAdapter: LinkHeaderAdapter by lazy { LinkHeaderAdapter(this) }
    private var petData: PetData? = null
    private var findMeData: ResponseMyProfile? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_edit_profile)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initMyProfileViewModel()
        binding?.titleTv?.text = requireContext().resources.getString(R.string.edit_profile)
        initClick()
        initSharedViewModel()
        textValidation()
    }

    //region init shared view model

    private fun initSharedViewModel() {
        sharedViewModel.dismissed.observe(viewLifecycleOwner, { isDissmis ->
            Timber.d("isDissmis $isDissmis")
            if (isDissmis) {
                initMyProfileViewModel()
            }
        })
    }

    //endregion


    //region convert image from url to bitmap encoding
    private fun convertImage(urlImage: String?) {
        Glide.with(this)
                .asBitmap()
                .load(Constant.BASE_URL_IMAGE + urlImage)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        imageBitmap = ImageUtil.encodeImage(resource)
                        Timber.d(imageBitmap)

                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })

//        imageBitmap = ImageUtil.encodeImage(ImageUtil.getBitmapFromURL(urlImage))
//        Timber.d(imageBitmap)
    }

    //endregion

    //region check data
    private fun checkData(): Boolean {
        return EditTextValidiation.validName(binding?.edName!!)
    }


    private fun textValidation() {
        binding?.edName?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validUserName(binding?.edName!!)
        }


    }
    //endregion

    //region init click

    private fun initClick() {
        binding?.imLayout?.setOnClickListener {
            pickImage()
        }
        binding?.btnImage?.setOnClickListener {
            pickImage()
        }
        binding?.btnClose?.setOnClickListener {
            imageBitmap = null
            binding?.btnImage?.visibility = View.VISIBLE
            binding?.btnClose?.visibility = View.GONE
            binding?.profileImage?.setImageResource(R.drawable.user_img)
        }

        binding?.switchMaterial?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                initTurnOnOffProfileViewModel()
            }
        }

        binding?.switchForFindMe?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                if (isChecked) {
                    initEditLinkViewModel(bodyEditLinkFindMe(1))
                } else {
                    initEditLinkViewModel(bodyEditLinkFindMe(0))
                }
            }
        }
        binding?.switchForPets?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                if (isChecked) {
                    initEditLinkViewModel(bodyEditLinkPet(1))
                } else {
                    initEditLinkViewModel(bodyEditLinkPet(0))
                }
            }
        }
        binding?.btnCancel?.setOnClickListener {
            findNavController().navigateUp()
        }
        binding?.btnSave?.setOnClickListener {
            if (checkData()) {
                initUpdateViewModel()
            }
        }

        binding?.cardForPets?.setOnClickListener {
            val action =
                    EditProfileFragmentDirections.actionEditProfileFragmentToAddPetSheet(petData)
            findNavController().navigate(action)
        }
        binding?.cardForFindMe?.setOnClickListener {
            val action =
                    EditProfileFragmentDirections.actionEditProfileFragmentToAddFindMeSheet(findMeData)
            findNavController().navigate(action)
        }

        binding?.edName?.editText?.setOnClickListener {
            binding?.edName?.editText?.isCursorVisible = true
        }
        binding?.bioInputLayout?.editText?.setOnClickListener {
            binding?.bioInputLayout?.editText?.isCursorVisible = true
        }
    }

    //endregion

    //region pick and capture image
    private fun pickImage() {
        PickImageDialog.build(PickSetup())
                .setOnPickResult {
                    if (it.error == null) {
                        imageBitmap = ImageUtil.encodeImage(it.bitmap)!!
                        binding?.profileImage?.setImageBitmap(it.bitmap)
                        binding?.btnImage?.visibility = View.GONE
                        binding?.btnClose?.visibility = View.VISIBLE
                    } else {

                        Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                    }
                }.setOnPickCancel {
                }.show(childFragmentManager)
    }

    //endregion

    //region bind data

    private fun bindMyProfile(myProfile: ResponseMyProfile) {
        binding?.myProfile = myProfile
    }

    private fun bindAdapter() {
        binding?.myLinkAdapter = myLinkAdapter
        binding?.allLinkAdapter = allLinkAdapter
    }

    //endregion

    //region init view model

    private fun initMyProfileViewModel() {
        bindAdapter()
        val viewModel = ViewModelProvider(this).get(MyProfileViewModel::class.java)
        viewModel.myProfile().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    myLinkAdapter.submitList(null, null)
                    bindMyProfile(response.data!!)
                    if (!response.data.imageUrl.isNullOrEmpty()) {
                        convertImage(response.data.imageUrl)
                    }
                    binding?.isPrivate = response.data.isProfilePrivate
                    allLinkAdapter.submitList(response.data.allLinks)
                    petData = response.data.petData
                    findMeData = response.data
                    if (response.data.myLinks?.isNotEmpty()!!) {
                        binding?.recyclerView3?.visibility = View.VISIBLE
                        binding?.tvEmpty?.visibility = View.GONE
                        myLinkAdapter.submitList(response.data.isDirectOn, response.data.myLinks)
                    } else {
                        binding?.recyclerView3?.visibility = View.INVISIBLE
                        binding?.tvEmpty?.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        13, 14 -> {

                        }

                        401 -> {
                            SessionEnded.dialogSessionEnded(
                                    requireActivity(),
                                    findNavController(),
                                    R.id.editProfileFragment,
                                    EditProfileFragmentDirections.actionEditProfileFragmentToLoginFragment()
                            )
                        }
                    }
                }

            }
        })

    }


    private fun initUpdateViewModel() {
        viewModel.updateProfile(bodyEditProfile()).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    Toast.makeText(requireContext(), requireContext().resources.getString(R.string.profile_updated), Toast.LENGTH_SHORT).show()
                    binding?.edName?.editText?.isCursorVisible = false
                    binding?.bioInputLayout?.editText?.isCursorVisible = false
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        13, 14 -> {

                        }
                        401 -> {
                            SessionEnded.dialogSessionEnded(
                                    requireActivity(),
                                    findNavController(),
                                    R.id.editProfileFragment,
                                    EditProfileFragmentDirections.actionEditProfileFragmentToLoginFragment()
                            )
                        }
                    }
                }

            }
        })

    }


    private fun bodyEditProfile(): BodyEditProfile {
        return BodyEditProfile(
                binding?.edName?.editText?.text.toString(), imageBitmap,
                PreferencesUtils(requireContext())
                        .getUserData(Constant.USER_DATA_KEY)?.email,
                binding?.bioInputLayout?.editText?.text.toString(),
                isProfilePrivate
        )
    }


    //endregion

    //region click on link
    override fun clickOnItems(item: Link, postion: Int) {
        when (item.linkType) {
            in 0..12, in 21..41 -> {
                val action =
                        EditProfileFragmentDirections.actionEditProfileFragmentToAddLinkSheet(item)
                findNavController().navigate(action)
            }
            in 13..20 -> {
                val action =
                        EditProfileFragmentDirections.actionEditProfileFragmentToAddPhoneSheet(item)
                findNavController().navigate(action)
            }
        }

    }


    override fun clickOnItems(item: MyLink, postion: Int) {
        when (item.linkType) {
            in 0..12, in 21..41 -> {
                val action =
                        EditProfileFragmentDirections.actionEditProfileFragmentToAddLinkSheet(item)
                findNavController().navigate(action)
            }
            in 13..20 -> {
                val action =
                        EditProfileFragmentDirections.actionEditProfileFragmentToAddPhoneSheet(item)
                findNavController().navigate(action)
            }
        }
    }
    //endregion

    //region init edit link view model

    private fun initEditLinkViewModel(bodyEditLink: BodyEditLink) {

        viewModelEditLink.updateLink(bodyEditLink).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    //  DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    //  DialogUtil.dismissDialog()
                    sharedViewModel.saveDismissed(true)
                }
                is Resource.Error -> {
                    // DialogUtil.dismissDialog()
                    when (response.code) {
                        13, 14 -> {

                        }
                        401 -> {
                            SessionEnded.dialogSessionEnded(
                                    requireActivity(),
                                    findNavController(),
                                    R.id.editProfileFragment,
                                    EditProfileFragmentDirections.actionEditProfileFragmentToLoginFragment()
                            )
                        }
                    }
                }

            }
        })

    }

    private fun bodyEditLinkFindMe(isActive: Int): BodyEditLink {
        return BodyEditLink(
                Constant.LINK_FIND_ME,
                findMeData?.findMeData?.name,
                null,
                0,
                isActive,
                findMeData?.findMeData?.address,
                findMeData?.findMeData?.information,
                findMeData?.findMeData?.bloodType,
                findMeData?.findMeData?.emergencyContactName,
                findMeData?.findMeData?.emergencyContactPhone,
                null,
                imageBitmap
        )
    }

    private fun bodyEditLinkPet(isActive: Int): BodyEditLink {
        return BodyEditLink(
                Constant.LINK_PET,
                petData?.name,
                null,
                0,
                isActive,
                null,
                petData?.information,
                null,
                petData?.emergencyContactName,
                petData?.emergencyContactPhone,
                petData?.type,
                null
        )
    }

    //endregion

    //region init turn on off viewmodel
    private fun initTurnOnOffProfileViewModel() {
        viewModelTurnOnOff.turnOnOffProfile().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    //   DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    //    DialogUtil.dismissDialog()
                    binding?.isPrivate = response.data?.data?.isProfilePrivate
                }
                is Resource.Error -> {
                    when (response.code) {
                        13, 14 -> {

                        }
                        401 -> {
                            SessionEnded.dialogSessionEnded(
                                    requireActivity(),
                                    findNavController(),
                                    R.id.editProfileFragment,
                                    EditProfileFragmentDirections.actionEditProfileFragmentToLoginFragment()
                            )
                        }
                    }
                }

            }
        })

    }


    //endregion


}