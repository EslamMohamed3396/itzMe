package com.itzme.ui.activity.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.itzme.R
import com.itzme.databinding.ActivityMainBinding
import com.itzme.utilits.LanguageUtils

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LanguageUtils.onAttach(newBase!!))
    }

    //region init


    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this

    }

    //endregion



    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }



}