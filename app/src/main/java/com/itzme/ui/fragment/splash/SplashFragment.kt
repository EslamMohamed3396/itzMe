package com.itzme.ui.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.databinding.FragmentSplashBinding
import com.itzme.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, container, R.layout.fragment_splash)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initClick()
    }

    private fun initClick() {
        binding.joinNowBtn.setOnClickListener {
            val action = SplashFragmentDirections.actionSplashFragmentToJoinNowFragment()
            findNavController().navigate(action)
        }
        binding.loginBtn.setOnClickListener {
            val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            findNavController().navigate(action)
        }

    }

}