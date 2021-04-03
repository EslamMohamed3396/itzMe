package com.itzme.ui.fragment.activeProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.databinding.ActiveProductFragmentBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.Constant
import com.itzme.utilits.PreferencesUtils

class ActiveProductFragment : BaseFragment<ActiveProductFragmentBinding>() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.active_product_fragment)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initClick()
        bindData()
    }

    //region init click
    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    //endregion


    //region bind data
    private fun bindData() {
        binding?.myLink = PreferencesUtils(requireContext()).getInstance()?.getUserData(Constant.USER_DATA_KEY)?.linkName
    }


    //endregion
}