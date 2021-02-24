package com.itzme.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.itzme.utilits.LanguageUtils
import com.itzme.utilits.PreferencesUtils
import com.itzme.utilits.Constant
import java.util.*


abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    protected lateinit var binding: B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLang()
    }

    protected open fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        @LayoutRes layoutRes: Int
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun checkLang() {
        var lang = ""
        if (PreferencesUtils(context).getInstance()?.getString(Constant.LANGUAGE_KEY)
                .isNullOrEmpty()
        ) {
            when (Locale.getDefault().displayLanguage) {
                Constant.ARABIC_LANGUAGE -> Constant.AR_LANG_CODE
                else -> Constant.EN_LANG_CODE
            }
            lang = Locale.getDefault().displayLanguage
        } else {
            Constant.LANG_ID =
                when (PreferencesUtils(context).getInstance()?.getString(Constant.LANGUAGE_KEY)) {
                    Constant.ARABIC_LANGUAGE -> Constant.AR_LANG_CODE
                    else -> Constant.EN_LANG_CODE
                }
            lang = PreferencesUtils(context).getInstance()?.getString(Constant.LANGUAGE_KEY)!!
        }
        PreferencesUtils(context).getInstance()?.putString(Constant.LANGUAGE_KEY, lang)
        LanguageUtils.setLocale(requireContext(), lang)
    }

}