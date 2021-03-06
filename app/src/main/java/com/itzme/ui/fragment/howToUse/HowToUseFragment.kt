package com.itzme.ui.fragment.howToUse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itzme.R

class HowToUseFragment : Fragment() {

    companion object {
        fun newInstance() = HowToUseFragment()
    }

    private lateinit var viewModel: HowToUseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_how_to_use, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HowToUseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}