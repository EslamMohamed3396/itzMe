package com.itzme.ui.fragment.myProfile

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.itzme.R
import com.itzme.data.models.itemMenu.ItemMenu
import com.itzme.databinding.FragmentMyProfileBinding
import com.itzme.ui.activity.main.ItemMenuAdapter
import com.itzme.ui.base.BaseFragment
import com.itzme.ui.base.IClickOnItems

class MyProfileFragment : BaseFragment<FragmentMyProfileBinding>(), IClickOnItems<ItemMenu> {

    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var viewModel: MyProfileViewModel

    private val adapter: ItemMenuAdapter by lazy { ItemMenuAdapter(this) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_my_profile)

    }

    private fun initClick() {
        binding?.toolbarImg?.setOnClickListener {
            if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START)!!) {
                binding?.drawerLayout?.closeDrawer(GravityCompat.START)
            } else {
                binding?.drawerLayout?.openDrawer(GravityCompat.START)
            }
        }
    }

    //region navigation drawer
    private fun initNavDrawer() {
        toggle = ActionBarDrawerToggle(requireActivity(), binding?.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding?.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    //endregion


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyProfileViewModel::class.java)
        initNavDrawer()
        initClick()
        fillDataToNavigation()
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