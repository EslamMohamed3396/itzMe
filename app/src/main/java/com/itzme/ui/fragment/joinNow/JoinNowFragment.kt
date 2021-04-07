package com.itzme.ui.fragment.joinNow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.itzme.R
import com.itzme.data.models.account.registerationAndLogin.request.BodyRegister
import com.itzme.databinding.FragmentJoinNowBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.*
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class JoinNowFragment : BaseFragment<FragmentJoinNowBinding>() {

    private lateinit var viewModel: JoinNowViewModel
    private lateinit var viewModelCheckName: CheckNameViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        hideNavigation()
        return bindView(inflater, container, R.layout.fragment_join_now)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initClick()
        initSearchEdit(binding?.userNameInputLayout)
        textValidation()
    }


    private fun initClick() {
        binding?.joinNowBtn?.setOnClickListener {
            if (checkData()) {
                initJoinViewModel()
            }
        }
        binding?.tvLogin?.setOnClickListener {
            val action = JoinNowFragmentDirections.actionJoinNowFragmentToLoginFragment2()
            findNavController().navigate(action)
        }

    }


    //region check data
    private fun checkData(): Boolean {
        return EditTextValidiation.validEmail(binding?.emailInputLayout!!) &&
                EditTextValidiation.validUserName(binding?.userNameInputLayout!!) &&
                EditTextValidiation.validPassword(binding?.passwordInputLayout!!) &&
                EditTextValidiation.validConfirmPassword(
                        binding?.passwordInputLayout!!,
                        binding?.confirmPasswordInputLayout!!
                )
    }

    private fun textValidation() {
        binding?.userNameInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validUserName(binding?.userNameInputLayout!!)
        }
        binding?.emailInputLayout?.editText?.doOnTextChanged { _, _, _, _ ->
            EditTextValidiation.validEmail(binding?.emailInputLayout!!)
        }
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

    //region register
    private fun bodyRegister(): BodyRegister {
        return BodyRegister(
                binding?.userNameInputLayout?.editText?.text.toString(),
                binding?.emailInputLayout?.editText?.text.toString(),
                binding?.passwordInputLayout?.editText?.text.toString()
        )
    }

    private fun initJoinViewModel() {
        viewModel = ViewModelProvider(this).get(JoinNowViewModel::class.java)

        viewModel.registration(bodyRegister()).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    PreferencesUtils(App.getContext()).getInstance()
                            ?.putUserData(
                                    Constant.USER_DATA_KEY,
                                    response.data?.data!!
                            )
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        19 -> {
                            binding?.userNameInputLayout?.error =
                                    requireContext().resources.getString(R.string.name_exist)
                        }
                        20 -> {
                            binding?.emailInputLayout?.error =
                                    requireContext().resources.getString(R.string.invalid_name)
                        }
                    }
                }

            }
        })
    }

    //endregion

    //region check name
    private fun initCheckNameViewModel(name: String) {
        viewModelCheckName = ViewModelProvider(this).get(CheckNameViewModel::class.java)
        viewModelCheckName.checkName(name).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                }
                is Resource.Error -> {
                    when (response.code) {
                        19 -> {
                            binding?.userNameInputLayout?.error =
                                    requireContext().resources.getString(R.string.name_exist)
                        }
                        20 -> {
                            binding?.userNameInputLayout?.error =
                                    requireContext().resources.getString(R.string.invalid_name)
                        }
                    }
                }

            }
        })
    }


    private fun initSearchEdit(inputLayout: TextInputLayout?) {
        Observable.create { emitter: ObservableEmitter<String?> ->
            inputLayout?.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                        s: CharSequence,
                        start: Int,
                        count: Int,
                        after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    emitter.onNext(s.toString())
                }

                override fun afterTextChanged(s: Editable) {

                }
            })
        }.debounce(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { s: String? ->
                    initCheckNameViewModel(s.toString())
                }


    }


    //endregion
}