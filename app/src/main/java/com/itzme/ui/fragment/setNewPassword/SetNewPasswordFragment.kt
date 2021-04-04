package com.itzme.ui.fragment.setNewPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.data.models.account.changePassword.request.BodyChangePassword
import com.itzme.databinding.FragmentSetNewPasswordBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.EditTextValidiation
import com.itzme.utilits.Resource

class SetNewPasswordFragment : BaseFragment<FragmentSetNewPasswordBinding>() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_set_new_password)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.toolbar?.titleTv?.text = requireContext().resources.getString(R.string.set_new_password)
        textValidation()
        initClick()
    }


    private fun initClick() {
        binding?.joinNowBtn?.setOnClickListener {
            if (checkData()) {
                initChangePasswordViewModel()
            }
        }
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }

    }


    //region check data
    private fun checkData(): Boolean {
        return EditTextValidiation.validPassword(binding?.oldPasswordInputLayout!!) &&
                EditTextValidiation.validPassword(binding?.passwordInputLayout!!) &&
                EditTextValidiation.validConfirmPassword(
                        binding?.passwordInputLayout!!,
                        binding?.confirmPasswordInputLayout!!
                )
    }

    private fun textValidation() {

        binding?.passwordInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validPassword(binding?.passwordInputLayout!!)
        }
        binding?.oldPasswordInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validPassword(binding?.oldPasswordInputLayout!!)
        }

        binding?.confirmPasswordInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validConfirmPassword(
                    binding?.passwordInputLayout!!,
                    binding?.confirmPasswordInputLayout!!
            )
        }
    }

    //endregion

    //region init view model

    private fun bodyChangePassword(): BodyChangePassword {
        return BodyChangePassword(
                binding?.oldPasswordInputLayout?.editText?.text.toString(),
                binding?.confirmPasswordInputLayout?.editText?.text.toString()
        )
    }

    private fun initChangePasswordViewModel() {
        val viewModel = ViewModelProvider(this).get(SetNewPasswordViewModel::class.java)
        viewModel.changePassword(bodyChangePassword()).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    Toast.makeText(requireContext(), response.data?.errorMessage!!, Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        14 -> {
                            binding?.oldPasswordInputLayout?.error =
                                    requireContext().resources.getString(R.string.invalidPassword)
                        }
                        else -> {

                        }
                    }
                }

            }
        })
    }

    //endregion

}