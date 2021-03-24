package com.itzme.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.itzme.ui.activity.main.MainActivity
import com.itzme.utilits.Constant
import com.itzme.utilits.LanguageUtils
import com.itzme.utilits.PreferencesUtils
import java.util.*


abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    protected var binding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLang()
    }

    protected open fun bindView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            @LayoutRes layoutRes: Int
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding?.lifecycleOwner = this
        return binding?.root
    }

    private fun checkLang() {
        var lang = ""
        if (PreferencesUtils(context).getInstance()?.getString(Constant.LANGUAGE_KEY)
                        .isNullOrEmpty()
        ) {
            when (Locale.getDefault().displayLanguage) {
                Constant.ARABIC_LANGUAGE -> Constant.ARABIC_LANGUAGE
                else -> Constant.ENGLISH_LANGUAGE
            }
            lang = Locale.getDefault().displayLanguage
        } else {
            Constant.LANG_NAME =
                    when (PreferencesUtils(context).getInstance()?.getString(Constant.LANGUAGE_KEY)) {
                        Constant.ARABIC_LANGUAGE -> Constant.ARABIC_LANGUAGE
                        else -> Constant.ENGLISH_LANGUAGE
                    }
            lang = PreferencesUtils(context).getInstance()?.getString(Constant.LANGUAGE_KEY)!!
        }
        PreferencesUtils(context).getInstance()?.putString(Constant.LANGUAGE_KEY, lang)
        LanguageUtils.setLocale(requireContext(), lang)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    protected fun showNavigation() {
      //  (activity as MainActivity).binding?.actionBar?.visibility = View.VISIBLE
       // (activity as MainActivity).binding?.layoutInclude?.visibility = View.VISIBLE
    }

    protected fun hideNavigation() {
      //  (activity as MainActivity).binding?.actionBar?.visibility = View.GONE
      //  (activity as MainActivity).binding?.layoutInclude?.visibility = View.GONE
    }
}