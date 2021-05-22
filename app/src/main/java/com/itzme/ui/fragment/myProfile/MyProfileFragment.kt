package com.itzme.ui.fragment.myProfile

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
import com.itzme.utilits.*
import com.itzme.view.SwitchTrackTextDrawable
import timber.log.Timber
import java.util.*


class MyProfileFragment : BaseFragment<FragmentMyProfileBinding>(), IClickOnItems<ItemMenu>,
    MyLinkAdapter.IClickOnLink {

    private var myLinkList: ArrayList<MyLink>? = null
    private lateinit var toggle: ActionBarDrawerToggle

    private val viewModel: MyProfileViewModel by viewModels()
    private val viewModelAbout: AboutViewModel by viewModels()
    private val viewModelToken: AddTokenViewModel by viewModels()
    private val viewModelDirect: DirectOnOffViewModel by viewModels()
    private val viewModelLogOut: LogOutViewModel by viewModels()
    private val viewModelTurnOnOff: TurnOnOffProfileViewModel by viewModels()
    private val viewModelChangePostionLinks: ChangePostionLinksViewModel by viewModels()

    private val adapter: ItemMenuAdapter by lazy { ItemMenuAdapter(this) }

    private val myLinkAdapter: MyLinkAdapter by lazy { MyLinkAdapter(this) }

    private var myLink: MyLink? = null

    private var oldPostion: Int? = null
    private var newPosition: Int? = null
    private var oldPostionPlusOne: Int? = null

    private var newPositionPlusOne: Int? = null


    private var fromPos = -1
    private var toPos = -1

    private var isFirstTime: Boolean? = true

    private val itemTouchHelper by lazy {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            0
        ) {
            override fun isLongPressDragEnabled(): Boolean {
                showToast("Swipe Now!!")
                return true
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return false
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

//                if (viewHolder.itemViewType != target.itemViewType) {
//                    return false
//                }
                val adapter = recyclerView.adapter as MyLinkAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition

                Timber.d("onMove from $from")
                Timber.d("onMove to $to")
//                Timber.d("onMove linkTypeName ${myLinkList!![from!!].linkTypeName}")
//                Timber.d("onMove linkType ${myLinkList!![from!!].linkType}")
//
//
//                Timber.d("onMove linkTypeName ${myLinkList!![to!!].linkTypeName}")
//                Timber.d("onMove linkType ${myLinkList!![to!!].linkType}")
//
//                when {
//                    from == 0 -> {
//                        initDirectOnOffViewModel(false, myLinkList!![to].linkType!!)
//                    }
//                    to == 0 -> {
//                        initDirectOnOffViewModel(false, myLinkList!![from].linkType!!)
//                    }
//                    else -> {
//            //                    Timber.d("onMove from after inc ${from.inc()}")
//            //                    Timber.d("onMove to after inc ${to.inc()}")
//                        initChangePostionLinksViewModel(
//                            type = myLinkList!![to].linkType!!,
//                            newPosition = from,
//                            replacedType = myLinkList!![to].linkType!!,
//                            oldPosition = from
//                        )
//                    }
//                }
                moveItem(from, to)
                adapter.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val from = viewHolder.adapterPosition

                Timber.d("onMove linkTypeName ${myLinkList!![from!!].linkTypeName}")

            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)

                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.alpha = 0.5f
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                val from = viewHolder.adapterPosition


//                Timber.d("onMove linkTypeName ${myLinkList!![from!!].linkTypeName}")
//                Timber.d("onMove linkType ${myLinkList!![from!!].linkType}")
//


                viewHolder?.itemView?.alpha = 1.0f
            }
        }

        ItemTouchHelper(simpleItemTouchCallback)
    }


    fun moveItem(from: Int, to: Int) {
        val item = myLinkList?.removeAt(from!!)
        myLinkList?.add(to!!, item!!)
    }


    private fun move(oldPos: Int, newPos: Int) {
        val temp = myLinkList?.get(oldPos)
        myLinkList?.set(oldPos, myLinkList?.get(newPos)!!)
        myLinkList?.set(newPos, temp!!)
        myLinkAdapter.notifyItemChanged(oldPos)
        myLinkAdapter.notifyItemChanged(newPos)

        Timber.d("oldPos $oldPos")
        Timber.d("newPos $newPos")
        when {
            oldPos == 0 -> {
                initDirectOnOffViewModel(false, myLinkList!![oldPos].linkType!!)
            }
            newPos == 0 -> {
                initDirectOnOffViewModel(false, myLinkList!![newPos].linkType!!)
            }
            else -> {
                val old = newPos
                val new = oldPos

                Timber.d("old linkType ${myLinkList!![newPos].linkTypeName}")
                Timber.d("oldPos ${new.inc()}")


                Timber.d("new linkType ${myLinkList!![oldPos].linkTypeName}")
                Timber.d("newPos ${old.inc()}")
                initChangePostionLinksViewModel(
                    type = myLinkList!![newPos].linkType!!,
                    newPosition = old.inc(),
                    replacedType = myLinkList!![oldPos].linkType!!,
                    oldPosition = new.inc()
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_my_profile)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isFirstTime=true
        initNavDrawer()
        initClick()
        fillDataToNavigation()
        initMyProfileViewModel()
        initAddTokenViewModel()
        initSharedViewModel()
    }


    private fun initSharedViewModel() {
        sharedViewModel.dismissed.observe(viewLifecycleOwner, { isDissmis ->
            Timber.d("isDissmis $isDissmis")
            if (isDissmis) {
                initMyProfileViewModel()
                sharedViewModel.saveDismissed(false)
            }
        })
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
                initDirectOnOffViewModel(true, myLink?.linkType!!)
            }
        }

        binding?.editProfileBtn?.setOnClickListener {
            val action =
                MyProfileFragmentDirections.actionMyProfileFragmentToEditProfileFragment()
            findContrller(action)
        }

        binding?.imQr?.setOnClickListener {
            val action = MyProfileFragmentDirections.actionMyProfileFragmentToMyQrCodeFragment()
            findContrller(action)
        }

        binding?.includeLayout?.toggleButton?.setTrackDrawable(
            SwitchTrackTextDrawable(
                requireContext(),
                R.string.itzme_off, R.string.itzme_on
            )
        )
        binding?.includeLayout?.toggleButton?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                initTurnOnOffProfileViewModel()
            }
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
                val action =
                    MyProfileFragmentDirections.actionMyProfileFragmentToContactFragment()
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
            5 -> {
                val action =
                    MyProfileFragmentDirections.actionMyProfileFragmentToReadItzMeFragment()
                findContrller(action)
            }
            6 -> {
                val action =
                    MyProfileFragmentDirections.actionMyProfileFragmentToMyQrCodeFragment()
                findContrller(action)
            }
            7 -> {
                initAboutViewModel()
            }
            8 -> {
                val action =
                    MyProfileFragmentDirections.actionMyProfileFragmentToHowToUseFragment()
                findContrller(action)
            }
            9 -> {
                val action =
                    MyProfileFragmentDirections.actionMyProfileFragmentToSettingsFragment()
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


    private fun dragDropItemRecyclerView() {


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT, 0
        ) {
            override fun isLongPressDragEnabled(): Boolean {
                showToast("Swipe Now!!")
                return true
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                toPos = target.getAdapterPosition();
                return false;
//                if (viewHolder.itemViewType != target.itemViewType) {
//                    return false
//                }
//
//                oldPostion = viewHolder.adapterPosition
//                newPosition = target.adapterPosition
////                Timber.d("onMove linkTypeName ${myLinkList!![oldPostion!!].linkTypeName}")
////                Timber.d("onMove linkType ${myLinkList!![oldPostion!!].linkType}")
////
////
////                Timber.d("onMove linkTypeName ${myLinkList!![newPosition!!].linkTypeName}")
////                Timber.d("onMove linkType ${myLinkList!![newPosition!!].linkType}")
//
//                val item = myLinkList?.removeAt(oldPostion!!)
//                myLinkList?.add(newPosition!!, item!!)
//                recyclerView.adapter!!.notifyItemMoved(oldPostion!!, newPosition!!)
//
//
//                // initDirectOnOffViewModel(false, myLinkList!![newPosition].linkType!!)
//                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val position = viewHolder.adapterPosition
//                myLinkList?.removeAt(position)
//                binding?.recyclerView6?.adapter!!.notifyItemRemoved(position)
            }

            override fun onSelectedChanged(
                viewHolder: RecyclerView.ViewHolder?,
                actionState: Int
            ) {
                when (actionState) {
                    ItemTouchHelper.ACTION_STATE_DRAG -> {
                        fromPos = viewHolder?.adapterPosition!!
                    }
                    ItemTouchHelper.ACTION_STATE_IDLE -> {
                        //Execute when the user dropped the item after dragging.
                        if (fromPos != -1 && toPos != -1
                            && fromPos != toPos
                        ) {
                            move(fromPos, toPos)
                            fromPos = -1
                            toPos = -1
                        }
                    }
                }
            }

        })
        itemTouchHelper.attachToRecyclerView(binding?.recyclerView6)


    }

    private fun bindMyProfile(myProfile: ResponseMyProfile) {
        binding?.myProfile = myProfile
        binding?.isDiriectOn = myProfile.isDirectOn
    }

    private fun bindAdapter() {
        binding?.myLinkAdapter = myLinkAdapter
        dragDropItemRecyclerView()
    }
    //endregion

    //region init view model

    private fun initMyProfileViewModel() {
        if (isFirstTime!!) {
            bindAdapter()
            isFirstTime = false
        }
        viewModel.myProfile().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()

                    bindMyProfile(response.data!!)

                    binding?.includeLayout?.isPrivate = response.data.isProfilePrivate
                    if (response.data.myLinks?.isNotEmpty()!!) {
                        myLinkAdapter.submitList(null, null, null)
                        myLinkAdapter.submitList(
                            response.data.isDirectOn,
                            response.data.myLinks,
                            null
                        )
                        myLink = response.data.myLinks[0]
                        myLinkList = response.data.myLinks as ArrayList<MyLink>
                    } else {
                        binding?.tvEmpty?.visibility = View.VISIBLE
                    }

                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()

                    when (response.code) {
                        13, 14 -> {

                        }
                        401 -> {
                            SessionEnded.dialogSessionEnded(
                                requireActivity(),
                                findNavController(),
                                R.id.myProfileFragment,
                                MyProfileFragmentDirections.actionMyProfileFragmentToLoginFragment()
                            )
                        }
                    }
                }

            }
        })

    }

    private fun initAboutViewModel() {

        viewModelAbout.about().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    openLink(response.data?.data?.facebookLink)
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()

                    when (response.code) {
                        401 -> {
                            SessionEnded.dialogSessionEnded(
                                requireActivity(),
                                findNavController(),
                                R.id.myProfileFragment,
                                MyProfileFragmentDirections.actionMyProfileFragmentToLoginFragment()
                            )
                        }
                    }
                }

            }
        })

    }

    private fun initDirectOnOffViewModel(isToogleStatus: Boolean, linkType: Int) {
        viewModelDirect.directOnOff(isToogleStatus, linkType)
            .observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        if (isToogleStatus) {
//                            binding?.isDiriectOn = isDirectOn!!
//                            myLinkAdapter.submitList(null, null, null)
//                            myLinkAdapter.submitList(
//                                !isDirectOn!!,
//                                myLinkList,
//                                null
//                            )
                            initMyProfileViewModel()
                        }
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

    private fun initChangePostionLinksViewModel(
        type: Int,
        newPosition: Int,
        replacedType: Int,
        oldPosition: Int
    ) {
        viewModelChangePostionLinks.changePostion(type, newPosition, replacedType, oldPosition)
            .observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        //initMyProfileViewModel()
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
                    binding?.includeLayout?.isPrivate = response.data?.data?.isProfilePrivate
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
                    val action =
                        MyProfileFragmentDirections.actionMyProfileFragmentToLoginFragment()
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
        Timber.d(App.getToken())
        return BodyAddToken(pushToken = App.getToken())
    }


    //endregion


    //region open buy itzme online store
    private fun openLink(url: String?) {
        Timber.d(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)


        val title = "Select a browser"

        val chooser = Intent.createChooser(intent, title)
        startActivity(chooser)
    }

    override fun clickOnItems(item: MyLink, postion: Int) {
        when (item.linkType) {
            in 0..12, in 21..41 -> {
                val action =
                    MyProfileFragmentDirections.actionMyProfileFragmentToAddLinkSheet(item)
                findNavController().navigate(action)
            }
            in 13..20 -> {
                val action =
                    MyProfileFragmentDirections.actionMyProfileFragmentToAddPhoneSheet(item)
                findNavController().navigate(action)
            }
        }
    }

    //edbregion
}