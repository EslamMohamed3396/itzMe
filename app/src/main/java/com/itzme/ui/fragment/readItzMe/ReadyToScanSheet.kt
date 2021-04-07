package com.itzme.ui.fragment.readItzMe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itzme.R
import com.itzme.databinding.SheetReadyToScanBinding
import com.itzme.ui.SharedViewModel

class ReadyToScanSheet : BottomSheetDialogFragment() {

    private lateinit var binding: SheetReadyToScanBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val args: ReadyToScanSheetArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.sheet_ready_to_scan, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        initSharedViewModel()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }


    //region init click
    private fun initClick() {
        binding.handle.setOnClickListener {
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

    }
    //endregion

    //region init sharedViewModel
    private fun initSharedViewModel() {
        sharedViewModel.saveState(args.stateNfcArgs)
        sharedViewModel.dismissed.observe(viewLifecycleOwner, { dismissed ->
            if (dismissed) {
                findNavController().navigateUp()
                val action = ReadyToScanSheetDirections.actionReadyToScanSheetToContactFragment()
                findNavController().navigate(action)
            }
        })
    }

    //endregion


}