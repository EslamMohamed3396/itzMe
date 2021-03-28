package com.itzme.ui.fragment.verificationCode

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.itzme.R
import com.itzme.data.models.enumVerfications.Verfication
import com.itzme.databinding.FragmentVerificationCodeBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.ui.fragment.forgetPasword.ForgetPasswordViewModel
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.Resource


class VerificationCodeFragment : BaseFragment<FragmentVerificationCodeBinding>() {


    private val currentCode = StringBuilder()
    private val args: VerificationCodeFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        hideNavigation()
        return bindView(inflater, container, R.layout.fragment_verification_code)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        insertCode()
        initClick()
        bindEmailTxt()
    }

    //region init
    private fun initClick() {
        binding?.joinNowBtn?.setOnClickListener {
            if (checkData(currentCode.toString())) {
                if (args.fromWhereArgs == Verfication.FORGET_PASSWORD) {
                    initConfirmViewModel(currentCode.toString())
                } else {
                    initConfirmChangeEmailViewModel(currentCode.toString())
                }
            }
        }

        binding?.tvResendCode?.setOnClickListener {
            if (args.fromWhereArgs == Verfication.FORGET_PASSWORD) {
                initForgotViewModel()
            } else {
                iniResendChangeEmailViewModel()
            }
        }

        binding?.imBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    //endregion


    //region check

    private fun checkData(code: String): Boolean {
        return code.isNotEmpty()
    }

    //endregion

    //region type code into edite text
    private fun insertCode() {

        binding?.ConfirmCodeOneInputLayout?.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                val edtChar: String =
                        binding?.ConfirmCodeOneInputLayout?.editText?.text.toString()

                if (edtChar.length == 1) {
                    currentCode.append(edtChar)

                    binding?.ConfirmCodeTwoInputLayout?.editText?.requestFocus()
                } else if (edtChar.length == 0) {
                    currentCode.deleteCharAt(0)
                    binding?.ConfirmCodeOneInputLayout?.editText?.requestFocus()
                }
            }
        })


        binding?.ConfirmCodeTwoInputLayout?.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                val edtChar: String =
                        binding?.ConfirmCodeTwoInputLayout?.editText?.text.toString()
                if (edtChar.length == 1) {
                    currentCode.append(edtChar)
                    binding?.ConfirmCodeThreeInputLayout?.editText?.requestFocus()
                } else if (edtChar.length == 0) {
                    currentCode.deleteCharAt(1)
                    binding?.ConfirmCodeOneInputLayout?.editText?.requestFocus()
                }
            }
        })
        binding?.ConfirmCodeThreeInputLayout?.editText?.addTextChangedListener(object :
                TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                val edtChar: String =
                        binding?.ConfirmCodeThreeInputLayout?.editText?.text.toString()
                if (edtChar.length == 1) {
                    currentCode.append(edtChar)
                    binding?.ConfirmCodeFourInputLayout?.editText?.requestFocus()
                } else if (edtChar.length == 0) {
                    currentCode.deleteCharAt(2)
                    binding?.ConfirmCodeTwoInputLayout?.editText?.requestFocus()
                }
            }
        })
        binding?.ConfirmCodeFourInputLayout?.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                val edtChar: String =
                        binding?.ConfirmCodeFourInputLayout?.editText?.text.toString()
                if (edtChar.length == 1) {
                    currentCode.append(edtChar)
                    binding?.ConfirmCodeFourInputLayout?.editText?.requestFocus()
                } else if (edtChar.length == 0) {
                    currentCode.deleteCharAt(2)
                    binding?.ConfirmCodeThreeInputLayout?.editText?.requestFocus()
                }
            }
        })
    }
    //endregion

    //region init confirm view model
    private fun initConfirmViewModel(code: String) {
        val viewModel = ViewModelProvider(this).get(VerificationCodeViewModel::class.java)

        viewModel.confirmCode(code).observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    val action =
                            VerificationCodeFragmentDirections.actionVerificationCodeFragmentToNewPasswordFragment(
                                    response.data?.data?.key
                            )
                    findNavController().navigate(action)

                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        13 -> {
                            Toast.makeText(
                                    requireContext(),
                                    requireContext().resources.getString(R.string.wrong_code),
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }

        })
    }

    //endregion

    // region init confirm change email view model
    private fun initConfirmChangeEmailViewModel(code: String) {
        val viewModel = ViewModelProvider(this).get(ConfirmChangeEmailViewModel::class.java)

        viewModel.confirmChangeEmail(code).observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    val action = VerificationCodeFragmentDirections.actionVerificationCodeFragmentToSettingsFragment()
                    findNavController().navigate(action)
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        13 -> {
                            Toast.makeText(
                                    requireContext(),
                                    requireContext().resources.getString(R.string.wrong_code),
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }

        })
    }

    //endregion

    //region init resend code
    private fun initForgotViewModel() {

        val viewModel = ViewModelProvider(this).get(ForgetPasswordViewModel::class.java)
        viewModel.forgotPassword(args.emailArgs!!).observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    clearText()
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        13 -> {

                        }
                    }
                }

            }

        })

    }

    private fun iniResendChangeEmailViewModel() {

        val viewModel = ViewModelProvider(this).get(ResendChangeEmailViewModel::class.java)
        viewModel.resendChangeEmail().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    clearText()
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        13 -> {

                        }
                    }
                }

            }

        })

    }

    //endregion

    //region Reset Data
    private fun clearText() {
        currentCode.clear()
        binding?.ConfirmCodeOneInputLayout?.editText?.setText("")
        binding?.ConfirmCodeTwoInputLayout?.editText?.setText("")
        binding?.ConfirmCodeThreeInputLayout?.editText?.setText("")
        binding?.ConfirmCodeFourInputLayout?.editText?.setText("")
        binding?.ConfirmCodeOneInputLayout?.editText?.requestFocus()
    }
    //endregion

    //region bind email to textView
    private fun bindEmailTxt() {
        binding?.tvEmail?.text = args.emailArgs
    }

    //endregion
}