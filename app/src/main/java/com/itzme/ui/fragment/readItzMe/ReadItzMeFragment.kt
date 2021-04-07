package com.itzme.ui.fragment.readItzMe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.data.models.stateNfc.StateNFC
import com.itzme.data.models.stateNfc.enmReadWriteItzME.ReadWriteNFC
import com.itzme.databinding.FragmentReadItzMeBinding
import com.itzme.ui.base.BaseFragment


class ReadItzMeFragment : BaseFragment<FragmentReadItzMeBinding>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_read_itz_me)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.toolbar?.titleTv?.text = requireContext().resources.getString(R.string.read_itzme)
        initClick()
        openSheet()
    }

    //region init click
    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }

    }
    //endregion


    //region show sheet scan me
    private fun openSheet() {
        val stateNFC = StateNFC(true, ReadWriteNFC.READ_NFC)
        val action = ReadItzMeFragmentDirections.actionReadItzMeFragmentToReadyToScanSheet(stateNFC)
        findNavController().navigate(action)
    }
    //endregion

}