package com.itzme.ui.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.databinding.FragmentSettingsBinding
import com.itzme.ui.activity.main.MainActivity
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.Constant
import com.itzme.utilits.LanguageUtils
import com.itzme.utilits.PreferencesUtils

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {


    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_settings)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind()
        initClick()
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

    private fun bind() {
        binding?.toolbar?.titleTv?.text = getString(R.string.settings)
        binding?.pref = PreferencesUtils(requireContext()).getInstance()
        binding?.constant = Constant
    }

    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener { findNavController().navigateUp() }


        binding?.tvChangeEmail?.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToChangeEmailFragment()
            findNavController().navigate(action)
        }
        binding?.btnEng?.setOnClickListener {
            if (Constant.LANG_NAME == Constant.ARABIC_LANGUAGE) {
                LanguageUtils.setLocale(requireContext(), Constant.ENGLISH_LANGUAGE)
                callSettingFragment()
            }
        }
        binding?.btnAr?.setOnClickListener {
            if (Constant.LANG_NAME == Constant.ENGLISH_LANGUAGE) {
                LanguageUtils.setLocale(requireContext(), Constant.ARABIC_LANGUAGE)
                callSettingFragment()
            }
        }
        binding?.tvChangePassword?.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToSetNewPasswordFragment()
            findNavController().navigate(action)
        }
    }

    private fun callSettingFragment() {
        (activity as MainActivity).finish()
        (activity as MainActivity).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
        (activity as MainActivity).startActivity((activity as MainActivity).intent)
        val action = SettingsFragmentDirections.actionSettingsFragmentSelf()
        findNavController().navigate(action)

    }

}