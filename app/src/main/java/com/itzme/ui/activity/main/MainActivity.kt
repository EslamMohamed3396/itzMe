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

class MainActivity : AppCompatActivity(), IClickOnItems<ItemMenu> {
    private lateinit var toggle: ActionBarDrawerToggle
    var binding: ActivityMainBinding? = null
    private val adapter: ItemMenuAdapter by lazy { ItemMenuAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initNavDrawer()
        initClick()
        fillDataToNavigation()
    }

    //region init
    private fun initClick() {
        binding?.toolbarImg?.setOnClickListener {
            if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START)!!) {
                binding?.drawerLayout?.closeDrawer(GravityCompat.START)
            } else {
                binding?.drawerLayout?.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this

    }

    //endregion

    //region navigation drawer
    private fun initNavDrawer() {
        toggle = ActionBarDrawerToggle(this, binding?.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding?.drawerLayout?.addDrawerListener(toggle)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LanguageUtils.onAttach(base!!))
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    //endregion

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    //region fill item name & image to navigation menu

    private fun fillDataToNavigation() {
        binding?.includeLayout?.menuAdapter = adapter
        val itemMenuList = ArrayList<ItemMenu>()
        itemMenuList.add(ItemMenu(1, "Home", R.drawable.home))
        itemMenuList.add(ItemMenu(2, "My Contact", R.drawable.my_contact))
        itemMenuList.add(ItemMenu(3, "Edit Profile", R.drawable.profile_user))
        itemMenuList.add(ItemMenu(4, "Activate", R.drawable.active_nfc))
        itemMenuList.add(ItemMenu(5, "Read Itzme", R.drawable.nfc))
        itemMenuList.add(ItemMenu(6, "My QR Code ", R.drawable.qr_code))
        itemMenuList.add(ItemMenu(7, "Buy Itzme", R.drawable.buy_itzme))
        itemMenuList.add(ItemMenu(8, "How To Use Itzme", R.drawable.question))
        itemMenuList.add(ItemMenu(9, "Settings", R.drawable.settings))
        itemMenuList.add(ItemMenu(10, "Log Out", R.drawable.logout))
        adapter.submitList(itemMenuList)
    }


    //endregion

    //region click on menu
    override fun clickOnItems(item: ItemMenu, postion: Int) {
    }

    //endregion
}