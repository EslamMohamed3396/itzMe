package com.itzme.ui.fragment.changeEmail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.data.models.enumVerfications.Verfication
import com.itzme.databinding.FragmentChangeEmailBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.ui.fragment.forgetPasword.ForgetPasswordFragmentDirections
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.EditTextValidiation
import com.itzme.utilits.Resource

class ChangeEmailFragment : BaseFragment<FragmentChangeEmailBinding>() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_change_email)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textValidation()
        initClick()
    }


    private fun initClick() {
        binding?.joinNowBtn?.setOnClickListener {
            if (checkData()) {
                initChangeEmailViewModel()
            }
        }
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }

    }


    //region check data
    private fun checkData(): Boolean {
        return EditTextValidiation.validEmail(binding?.emailInputLayout!!)
    }

    private fun textValidation() {

        binding?.emailInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validEmail(binding?.emailInputLayout!!)
        }

    }

    //endregion


    //region init view model

    private fun initChangeEmailViewModel() {
        val viewModel = ViewModelProvider(this).get(ChangeEmailViewModel::class.java)
        viewModel.changeEmail(binding?.emailInputLayout?.editText?.text.toString()).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    val action = ForgetPasswordFragmentDirections
                            .actionForgetPasswordFragmentToVerificationCodeFragment(response.data?.data?.email,
                                    null, Verfication.CHANGE_EMAIL)
                    findNavController().navigate(action)
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        5 -> {
                            binding?.emailInputLayout?.error =
                                    requireContext().resources.getString(R.string.email_exist)
                        }
                    }
                }

            }
        })
    }

    //endregion
}