package com.itzme.ui.fragment.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itzme.R
import com.itzme.databinding.SheetPetContactBinding

class PetSheetContact : BottomSheetDialogFragment() {

    lateinit var binding: SheetPetContactBinding
    private val args: PetSheetContactArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = SheetPetContactBinding.inflate(inflater, container, false)
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
        binding.petData = args.petContactArgs
    }

    //endregion

    //region init click
    private fun initClick() {
        binding.handle.setOnClickListener {
            dismiss()
        }

    }
    //endregion


}