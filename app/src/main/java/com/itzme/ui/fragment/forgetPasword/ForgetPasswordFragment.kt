package com.itzme.ui.fragment.forgetPasword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.data.models.enumVerfications.Verfication
import com.itzme.databinding.FragmentForgetPasswordBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.CheckValidData
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.EditTextValidiation
import com.itzme.utilits.Resource

class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>() {


    private lateinit var viewModel: ForgetPasswordViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        hideNavigation()
        return bindView(inflater, container, R.layout.fragment_forget_password)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textValidation()
        initClick()
    }


    //region check data
    private fun checkData(): Boolean {
        return CheckValidData.checkEmail(binding?.emailInputLayout!!)

    }

    private fun textValidation() {
        binding?.emailInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validEmail(binding?.emailInputLayout!!)
        }

    }

    //endregion

    private fun initClick() {
        binding?.nextBtn?.setOnClickListener {
            if (checkData()) {
                initForgotViewModel()
            }
        }
        binding?.btnBack?.setOnClickListener {
            findNavController().navigateUp()
        }
        binding?.imBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun initForgotViewModel() {
        val email = binding?.emailInputLayout?.editText?.text.toString()
        viewModel = ViewModelProvider(this).get(ForgetPasswordViewModel::class.java)
        viewModel.forgotPassword(email).observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    val action = ForgetPasswordFragmentDirections
                            .actionForgetPasswordFragmentToVerificationCodeFragment(response.data?.data?.email,
                                    response.data?.data?.code, Verfication.FORGET_PASSWORD)
                    findNavController().navigate(action)
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        13 -> {
                            binding?.emailInputLayout?.error =
                                    requireContext().resources.getString(R.string.email_or_password_wrong)
                        }
                    }
                }

            }

        })

    }

}