package com.itzme.ui.fragment.myQrCode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itzme.R

class MyQrCodeFragment : Fragment() {

    companion object {
        fun newInstance() = MyQrCodeFragment()
    }

    private lateinit var viewModel: MyQrCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_qr_code, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyQrCodeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}