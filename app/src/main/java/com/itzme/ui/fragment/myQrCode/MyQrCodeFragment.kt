package com.itzme.ui.fragment.myQrCode

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.databinding.FragmentMyQrCodeBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.Constant
import com.itzme.utilits.PreferencesUtils

class MyQrCodeFragment : BaseFragment<FragmentMyQrCodeBinding>() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_my_qr_code)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindTitle()
        bindQrCode()
        initClick()
    }

    //region bind data
    private fun bindTitle() {
        binding?.toolbar?.titleTv?.text = requireContext().resources.getString(R.string.my_qr_code)

    }


    private fun bindQrCode() {
        binding?.myLinkName =
                PreferencesUtils(requireContext()).getUserData(Constant.USER_DATA_KEY)?.linkName!!
        //  generateQRCode(PreferencesUtils(requireContext()).getUserData(Constant.USER_DATA_KEY)?.linkName!!)
    }
    //endregion


    //region click

    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener { findNavController().navigateUp() }

        binding?.btnShareMyLink?.setOnClickListener {
            shareMyLink()
        }
    }

    //endregion

    //region share my link


    private fun shareMyLink() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                    Intent.EXTRA_TEXT,
                    PreferencesUtils(requireContext()).getUserData(Constant.USER_DATA_KEY)?.linkName!!
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }
    //endregion
}