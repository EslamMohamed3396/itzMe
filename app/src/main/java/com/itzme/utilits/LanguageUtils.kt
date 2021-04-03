package com.itzme.utilits

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import java.util.*


object LanguageUtils {


    fun onAttach(context: Context): Context? {
        val lang = getPersistedData(context)
        return setLocale(context, lang)
    }

    fun setLocale(context: Context, language: String?): Context? {
        persist(context, language)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)
    }

    private fun getPersistedData(context: Context): String? {
        return checkLang(context)
    }

    private fun persist(context: Context, language: String?) {
        PreferencesUtils(context).getInstance()?.putString(Constant.LANGUAGE_KEY, language!!)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String?): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String?): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }


    private fun checkLang(context: Context): String {
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
        return lang
    }


}