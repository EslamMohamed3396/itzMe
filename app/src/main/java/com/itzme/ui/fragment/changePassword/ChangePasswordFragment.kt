package com.itzme.ui.fragment.changePassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.databinding.FragmentChangePasswordBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.CheckValidData
import com.itzme.utilits.EditTextValidiation

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>() {


    private lateinit var viewModel: ChangePasswordViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_change_password)
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

    private fun initChangePasswordViewModel() {
        viewModel = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)

    }

}