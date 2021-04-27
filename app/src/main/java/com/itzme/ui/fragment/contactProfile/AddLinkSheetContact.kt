package com.itzme.ui.fragment.contactProfile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.itzme.R
import com.itzme.databinding.SheetSocialPhoneContactBinding
import com.itzme.utilits.ClickOnLink


class AddLinkSheetContact : BottomSheetDialogFragment() {

    lateinit var binding: SheetSocialPhoneContactBinding
    private val args: AddLinkSheetContactArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = SheetSocialPhoneContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        bindData()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }


    //region bind data

    private fun bindData() {
        val clickOnLink = ClickOnLink()
        binding.clickLink = clickOnLink
        binding.itemLink = args.linkContactProfile
    }

    //endregion

    //region init click
    private fun initClick() {
        binding.handle.setOnClickListener {
            dismiss()
        }
        binding.copyBtn.setOnClickListener {
            copyLink()
        }

    }
    //endregion


    private fun copyLink() {
        val clipboard: ClipboardManager? = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText("Copied", binding.itemLink?.link!!)
        clipboard?.setPrimaryClip(clip)
        Snackbar.make(binding.layout, "Copied", Snackbar.LENGTH_SHORT).show()
    }

}