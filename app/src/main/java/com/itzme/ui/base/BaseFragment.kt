package com.itzme.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.itzme.ui.SharedViewModel
import com.itzme.utilits.Constant
import com.itzme.utilits.LanguageUtils
import com.itzme.utilits.PreferencesUtils
import timber.log.Timber
import java.util.*


abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    protected var binding: B? = null
    val sharedViewModel: SharedViewModel by activityViewModels()

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
            Constant.LANG_NAME = when (Locale.getDefault().displayLanguage) {
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
        LanguageUtils.setLocale(requireContext(), lang)
        Timber.d("lang : $lang")
        Timber.d("LANG_NAME : ${Constant.LANG_NAME}")
    }

    override fun onPause() {
        super.onPause()
        binding?.unbind()
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