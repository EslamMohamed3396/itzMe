package com.itzme.ui.fragment.newPassword

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.itzme.R
import com.itzme.data.models.registerLoginModel.resetPassword.request.BodyResetPassword
import com.itzme.databinding.FragmentNewPasswordBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.EditTextValidiation
import com.itzme.utilits.Resource


class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding>() {


    private lateinit var viewModel: NewPasswordViewModel

    private val args: NewPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideNavigation()
        return bindView(inflater, container, R.layout.fragment_new_password)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initClick()
        textValidation()
    }


    //region check data
    private fun checkData(): Boolean {
        return EditTextValidiation.validPassword(binding?.passwordInputLayout!!) &&
                EditTextValidiation.validConfirmPassword(
                    binding?.passwordInputLayout!!,
                    binding?.confirmPasswordInputLayout!!
                )
    }

    private fun textValidation() {

        binding?.passwordInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validPassword(binding?.passwordInputLayout!!)
        }

        binding?.confirmPasswordInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validConfirmPassword(
                binding?.passwordInputLayout!!,
                binding?.confirmPasswordInputLayout!!
            )
        }
    }

    //endregion


    //region init
    private fun initClick() {

        binding?.joinNowBtn?.setOnClickListener {
            if (checkData()) {
                initSetPasswordViewModel()
            }
        }


        binding?.imBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    //endregion

    //region set password view model
    private fun initSetPasswordViewModel() {
        viewModel = ViewModelProvider(this).get(NewPasswordViewModel::class.java)

        viewModel.resetPassword(bodyResetPassword()).observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    initSuccessDialog()
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        7, 13 -> {
                            binding?.confirmPasswordInputLayout?.error =
                                requireContext().resources.getString(R.string.invalidPassword)
                        }
                    }
                }

            }
        })
    }

    private fun bodyResetPassword(): BodyResetPassword {
        return BodyResetPassword(
            args.userIdArgs,
            binding?.confirmPasswordInputLayout?.editText?.text?.toString()
        )
    }
    //endregion


    //region show sucess dialog
    private fun initSuccessDialog() {

        lateinit var dialog: Dialog

        dialog = Dialog(requireContext(), R.style.FullHeightDialog)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.setContentView(R.layout.dialog_success)

        val btnClose = dialog.findViewById<FloatingActionButton>(R.id.floating_action_button)
        dialog.setCancelable(false)
        if (!dialog.isShowing) {
            dialog.show()
        }
        btnClose.setOnClickListener {
            val action = NewPasswordFragmentDirections.actionNewPasswordFragmentToLoginFragment()
            findNavController().navigate(action)
            dialog.dismiss()
        }

    }


    //endregion

}