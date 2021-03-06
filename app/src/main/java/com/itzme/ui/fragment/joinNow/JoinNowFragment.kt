package com.itzme.ui.fragment.joinNow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itzme.R

class JoinNowFragment : Fragment() {

    companion object {
        fun newInstance() = JoinNowFragment()
    }

    private lateinit var viewModel: JoinNowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_join_now, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JoinNowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}