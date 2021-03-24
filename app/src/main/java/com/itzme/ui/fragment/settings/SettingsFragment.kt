package com.itzme.ui.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.databinding.FragmentSettingsBinding
import com.itzme.ui.base.BaseFragment

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
        initActionBar()
        initClick()
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

    private fun initActionBar() {
        binding?.toolbar?.titleTv?.text = getString(R.string.settings)
    }

    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener { findNavController().navigateUp() }

    }

}