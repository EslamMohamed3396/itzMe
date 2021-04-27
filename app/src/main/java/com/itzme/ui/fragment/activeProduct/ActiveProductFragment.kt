package com.itzme.ui.fragment.activeProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.data.models.stateNfc.StateNFC
import com.itzme.data.models.stateNfc.enmReadWriteItzME.ReadWriteNFC
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
        binding?.toolbar?.titleTv?.text = requireContext().resources.getString(R.string.active_product)
        initClick()
        bindData()
    }

    //region init click
    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }

        binding?.joinNowBtn?.setOnClickListener {
            val stateNFC = StateNFC(true, ReadWriteNFC.WRITE_NFC)

            val action = ActiveProductFragmentDirections.actionActiveProductFragmentToReadyToScanSheet(stateNFC)
            findNavController().navigate(action)
        }
    }
    //endregion


    //region bind data
    private fun bindData() {
        binding?.myLink = PreferencesUtils(requireContext()).getInstance()?.getUserData(Constant.USER_DATA_KEY)?.linkName
    }


    //endregion
}