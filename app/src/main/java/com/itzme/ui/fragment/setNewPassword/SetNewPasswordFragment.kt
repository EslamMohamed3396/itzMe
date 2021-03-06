package com.itzme.ui.fragment.setNewPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itzme.R

class SetNewPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = SetNewPasswordFragment()
    }

    private lateinit var viewModel: SetNewPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_new_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SetNewPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}