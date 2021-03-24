package com.itzme.ui.activity.main

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.itzme.R
import com.itzme.data.models.itemMenu.ItemMenu
import com.itzme.databinding.ActivityMainBinding
import com.itzme.ui.base.IClickOnItems
import com.itzme.utilits.LanguageUtils

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()

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