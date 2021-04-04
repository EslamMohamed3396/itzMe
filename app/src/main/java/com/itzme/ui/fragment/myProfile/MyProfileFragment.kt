package com.itzme.ui.fragment.myProfile

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.itzme.R
import com.itzme.data.models.itemMenu.ItemMenu
import com.itzme.data.models.notification.request.BodyAddToken
import com.itzme.data.models.profile.myProfile.response.MyLink
import com.itzme.data.models.profile.myProfile.response.ResponseMyProfile
import com.itzme.databinding.FragmentMyProfileBinding
import com.itzme.ui.base.BaseFragment
import com.itzme.ui.base.IClickOnItems
import com.itzme.ui.fragment.myProfile.adapter.ItemMenuAdapter
import com.itzme.ui.fragment.myProfile.adapter.MyLinkAdapter
import com.itzme.ui.fragment.myProfile.viewModels.*
import com.itzme.utilits.App
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.PreferencesUtils
import com.itzme.utilits.Resource

class MyProfileFragment : BaseFragment<FragmentMyProfileBinding>(), IClickOnItems<ItemMenu> {

    private lateinit var toggle: ActionBarDrawerToggle

    private val viewModel: MyProfileViewModel by viewModels()
    private val viewModelToken: AddTokenViewModel by viewModels()
    private val viewModelDirect: DirectOnOffViewModel by viewModels()
    private val viewModelLogOut: LogOutViewModel by viewModels()
    private val viewModelTurnOnOff: TurnOnOffProfileViewModel by viewModels()

    private val adapter: ItemMenuAdapter by lazy { ItemMenuAdapter(this) }
    private val myLinkAdapter: MyLinkAdapter by lazy { MyLinkAdapter() }
    private var myLink: MyLink? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_my_profile)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initNavDrawer()
        initClick()
        fillDataToNavigation()
        initMyProfileViewModel()
        initAddTokenViewModel()
    }

    private fun initClick() {
        binding?.toolbarImg?.setOnClickListener {
            if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START)!!) {
                binding?.drawerLayout?.closeDrawer(GravityCompat.START)
            } else {
                binding?.drawerLayout?.openDrawer(GravityCompat.START)
            }
        }

        binding?.directOffBtn?.setOnClickListener {
            if (myLink != null) {
                initDirectOnOffViewModel()
            }
        }

        binding?.editProfileBtn?.setOnClickListener {
            val action = MyProfileFragmentDirections.actionMyProfileFragmentToEditProfileFragment()
            findContrller(action)
        }

        binding?.imQr?.setOnClickListener {
            val action = MyProfileFragmentDirections.actionMyProfileFragmentToMyQrCodeFragment()
            findContrller(action)
        }

        binding?.includeLayout?.toggleButton?.setOnClickListener {
            initTurnOnOffProfileViewModel()
        }

    }

    //region navigation drawer
    private fun initNavDrawer() {
        toggle = ActionBarDrawerToggle(
                requireActivity(),
                binding?.drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        binding?.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    //endregion


    //region fill item name & image to navigation menu

    private fun fillDataToNavigation() {
        binding?.includeLayout?.menuAdapter = adapter
        val itemMenuList = ArrayList<ItemMenu>()
        itemMenuList.add(
                ItemMenu(
                        1,
                        requireContext().resources.getString(R.string.home),
                        R.drawable.home
                )
        )
        itemMenuList.add(ItemMenu(2, getString(R.string.my_account), R.drawable.my_contact))
        itemMenuList.add(ItemMenu(3, getString(R.string.edit_profile), R.drawable.profile_user))
        itemMenuList.add(ItemMenu(4, getString(R.string.active), R.drawable.active_nfc))
        itemMenuList.add(ItemMenu(5, getString(R.string.read_itzme), R.drawable.nfc))
        itemMenuList.add(ItemMenu(6, getString(R.string.my_qr), R.drawable.qr_code))
        itemMenuList.add(ItemMenu(7, getString(R.string.buy_itzme), R.drawable.buy_itzme))
        itemMenuList.add(ItemMenu(8, getString(R.string.how_to_use), R.drawable.question))
        itemMenuList.add(ItemMenu(9, getString(R.string.settings), R.drawable.settings))
        itemMenuList.add(ItemMenu(10, getString(R.string.log_out), R.drawable.logout))
        adapter.submitList(itemMenuList)
    }


    //endregion

    //region click on menu
    override fun clickOnItems(item: ItemMenu, postion: Int) {
        openFragments(item.id)
    }

    //endregion

    private fun openFragments(id: Int) {
        binding?.drawerLayout?.closeDrawer(GravityCompat.START)

        when (id) {
            1 -> {

            }
            2 -> {
                val action = MyProfileFragmentDirections.actionMyProfileFragmentToContactFragment()
                findContrller(action)
            }
            3 -> {
                val action =
                        MyProfileFragmentDirections.actionMyProfileFragmentToEditProfileFragment()
                findContrller(action)
            }
            4 -> {
                val action =
                        MyProfileFragmentDirections.actionMyProfileFragmentToActiveListFragment()
                findContrller(action)
            }
            6 -> {
                val action = MyProfileFragmentDirections.actionMyProfileFragmentToMyQrCodeFragment()
                findContrller(action)
            }
            8 -> {
                val action = MyProfileFragmentDirections.actionMyProfileFragmentToHowToUseFragment()
                findContrller(action)
            }
            9 -> {
                val action = MyProfileFragmentDirections.actionMyProfileFragmentToSettingsFragment()
                findContrller(action)
            }
            10 -> {
                initLogOutViewModel()
            }
        }
    }

    private fun clearSession() {
        PreferencesUtils(requireContext()).clear()
    }

    private fun findContrller(action: NavDirections) {
        findNavController().navigate(action)
    }

    //region bind data

    private fun bindMyProfile(myProfile: ResponseMyProfile) {
        binding?.myProfile = myProfile
    }

    private fun bindAdapter() {
        binding?.myLinkAdapter = myLinkAdapter
    }
    //endregion

    //region init view model

    private fun initMyProfileViewModel() {
        bindAdapter()
        viewModel.myProfile().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {

                    DialogUtil.dismissDialog()

                    bindMyProfile(response.data!!)
                    if (response.data.myLinks?.isNotEmpty()!!) {
                        myLinkAdapter.submitList(response.data.myLinks)
                        myLink = response.data.myLinks[0]
                    }
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()

                    when (response.code) {
                        13, 14 -> {

                        }
                    }
                }

            }
        })

    }

    private fun initDirectOnOffViewModel() {
        viewModelDirect.directOnOff(true, myLink?.linkType!!).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    initMyProfileViewModel()
                }
                is Resource.Error -> {
                    when (response.code) {
                        13, 14 -> {

                        }
                    }
                }

            }
        })

    }

    private fun initTurnOnOffProfileViewModel() {
        viewModelTurnOnOff.turnOnOffProfile().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    binding?.includeLayout?.myProfile = response.data?.data
                }
                is Resource.Error -> {
                    when (response.code) {
                        13, 14 -> {

                        }
                    }
                }

            }
        })

    }

    private fun initAddTokenViewModel() {
        viewModelToken.addToken(bodyAddToken()).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {

                }
                is Resource.Error -> {
                    when (response.code) {
                        401 -> {

                        }
                    }
                }

            }
        })

    }

    private fun initLogOutViewModel() {
        viewModelLogOut.logOut(bodyAddToken()).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())

                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()

                    clearSession()
                    val action = MyProfileFragmentDirections.actionMyProfileFragmentToLoginFragment()
                    findContrller(action)
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()

                    when (response.code) {
                        401 -> {

                        }
                    }
                }

            }
        })

    }

    private fun bodyAddToken(): BodyAddToken {
        return BodyAddToken(pushToken = App.getToken())
    }


    //endregion
}