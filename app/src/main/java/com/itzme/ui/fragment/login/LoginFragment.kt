package com.itzme.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.data.models.account.registerationAndLogin.request.BodyLogin
import com.itzme.data.models.account.registerationAndLogin.response.ResponseRegisterAndLogin
import com.itzme.databinding.FragmentLoginBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.*

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        hideNavigation()
        return bindView(inflater, container, R.layout.fragment_login)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initClick()
        textValidation()

    }


    //region check data
    private fun checkData(): Boolean {
        return CheckValidData.checkEmail(binding?.emailInputLayout!!) &&
                CheckValidData.checkPassword(binding?.passwordInputLayout!!)

    }

    private fun textValidation() {
        binding?.emailInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validEmail(binding?.emailInputLayout!!)
        }
        binding?.passwordInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validPassword(binding?.passwordInputLayout!!)
        }

    }

    //endregion
    private fun initClick() {
        binding?.joinNowBtn?.setOnClickListener {
            if (checkData()) {
                initLoginViewModel()
            }
        }
        binding?.tvSignUp?.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToJoinNowFragment()
            findNavController().navigate(action)
        }
        binding?.tvForgetPassword?.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment()
            findNavController().navigate(action)
        }
    }


    //region login view model


    private fun bodyLogin(): BodyLogin {
        return BodyLogin(
                binding?.emailInputLayout?.editText?.text.toString(),
                binding?.passwordInputLayout?.editText?.text.toString()
        )
    }

    private fun initLoginViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.login(bodyLogin()).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    saveData(response.data!!)

                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        13, 14 -> {
                            binding?.passwordInputLayout?.error =
                                    requireContext().resources.getString(R.string.email_or_password_wrong)
                        }
                    }
                }

            }
        })
    }

    private fun saveData(response: ResponseRegisterAndLogin) {
        PreferencesUtils(App.getContext()).getInstance()
                ?.putUserData(
                        Constant.USER_DATA_KEY,
                        response.data!!
                )
        PreferencesUtils(App.getContext()).getInstance()
                ?.putBoolean(
                        Constant.IS_USER_LOGIN,
                        true
                )
        goToMyProfile()
    }

    private fun goToMyProfile() {
        val action = LoginFragmentDirections.actionLoginFragmentToMyProfileFragment()
        findNavController().navigate(action)
    }
}