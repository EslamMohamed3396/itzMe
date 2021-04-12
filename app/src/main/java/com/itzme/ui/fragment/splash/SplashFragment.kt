package com.itzme.ui.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.databinding.FragmentSplashBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.utilits.Constant
import com.itzme.utilits.PreferencesUtils


class SplashFragment : BaseFragment<FragmentSplashBinding>() {


    private var mAnimationJoin: Animation? = null
    private var mAnimationLogin: Animation? = null
    private var mAnimationLogo: Animation? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        hideNavigation()
        return bindView(inflater, container, R.layout.fragment_splash)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        animation()
        initClick()
        goToFragment()
    }

    private fun animation() {
        mAnimationJoin = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_slide_from_bottom)
        mAnimationLogin = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_slide_from_bottom_2)
        mAnimationLogo = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_slide_from_rigt_left)
        binding?.loginBtn?.animation = mAnimationLogin
        binding?.joinNowBtn?.animation = mAnimationJoin
        binding?.imLogo?.animation = mAnimationLogo

    }

    private fun checkLogin(): Boolean {
        return PreferencesUtils(requireContext()).getInstance()?.getBoolean(Constant.IS_USER_LOGIN)!!
    }

    private fun goToFragment() {
        if (checkLogin()) {
            val action = SplashFragmentDirections.actionSplashFragmentToMyProfileFragment()
            findNavController().navigate(action)
        }
    }

    private fun initClick() {
        binding?.joinNowBtn?.setOnClickListener {
            val action = SplashFragmentDirections.actionSplashFragmentToJoinNowFragment()
            findNavController().navigate(action)
        }
        binding?.loginBtn?.setOnClickListener {
            val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            findNavController().navigate(action)
        }

    }

    override fun onPause() {
        super.onPause()
        mAnimationJoin?.cancel()
        mAnimationLogin?.cancel()
        mAnimationLogo?.cancel()
    }

}