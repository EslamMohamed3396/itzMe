package com.itzme.ui.activity.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itzme.R
import com.itzme.utilits.LanguageUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LanguageUtils.onAttach(base!!))
    }
}