package com.itzme.ui.fragment.howToUse

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.itzme.R
import com.itzme.data.models.stateNfc.StateNFC
import com.itzme.data.models.stateNfc.enmReadWriteItzME.ReadWriteNFC
import com.itzme.data.models.tags.howToUse.response.HowToUse
import com.itzme.databinding.FragmentHowToUseBinding
import com.itzme.ui.base.BaseFragment

class HowToUseFragment : BaseFragment<FragmentHowToUseBinding>() {

    private val howToUseAdapter by lazy {
        HowToUseAdapter()
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_how_to_use)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.toolbar?.titleTv?.text = requireContext().resources.getString(R.string.how_to_use)
        initClick()
        setUpViewpager()
        initSharedViewModel()
    }



    //region init sharedViewModel
    private fun initSharedViewModel() {
        sharedViewModel.notifyToMyProfile.observe(viewLifecycleOwner, { notify ->
            if (notify) {
                if (findNavController().currentDestination?.id == R.id.howToUseFragment) {
                    val action =
                        HowToUseFragmentDirections.actionHowToUseFragmentToMyProfileFragment()
                    findNavController().navigate(action)
                } else if (findNavController().currentDestination?.id == R.id.readyToScanSheet) {
                    findNavController().navigateUp()
                    val action =
                        HowToUseFragmentDirections.actionHowToUseFragmentToMyProfileFragment()
                    findNavController().navigate(action)
                }
                sharedViewModel.saveNotify(false)
            }
        })
    }

    //endregion

    //region show sheet scan me
    private fun openSheet() {
        val stateNFC = StateNFC(true, ReadWriteNFC.WRITE_NFC)
        val action = HowToUseFragmentDirections.actionHowToUseFragmentToReadyToScanSheet(stateNFC)
        findNavController().navigate(action)
    }
    //endregion

    //region init Click

    private fun initClick() {
        binding?.nextBtn?.setOnClickListener {
            if (binding?.imageSlider?.currentItem!! + 1 < howToUseAdapter.itemCount) {
                binding?.imageSlider!!.currentItem += 1
            } else {
                openSheet()
            }
        }
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }

        binding?.skipBtn?.setOnClickListener {
            if (binding?.imageSlider?.currentItem!! + 1 < howToUseAdapter.itemCount) {
                binding?.imageSlider!!.currentItem = howToUseAdapter.itemCount - 1
            }
        }
    }

    //endregion


    //region bind Data


    private fun setUpViewpager() {
        val howToUserList = ArrayList<HowToUse>()
        howToUserList.add(
                HowToUse(
                    1, R.drawable.how_iphone,
                    requireContext().resources.getString(R.string.how_iphone),
                    requireContext().resources.getString(R.string.content1_iphone),
                    requireContext().resources.getString(R.string.content2_iphone),

                    )
        )
        howToUserList.add(
                HowToUse(
                        2, R.drawable.how_to_user_android,
                        requireContext().resources.getString(R.string.how_android),
                        requireContext().resources.getString(R.string.content1_android),
                        requireContext().resources.getString(R.string.content2_android),
                )
        )
        howToUserList.add(
                HowToUse(
                        3,
                        R.drawable.how_to_use_direct,
                        requireContext().resources.getString(R.string.how_direct),
                        requireContext().resources.getString(R.string.content1_direct),
                        requireContext().resources.getString(R.string.content2_direct)
                )
        )
        howToUserList.add(
                HowToUse(
                        4,
                        R.drawable.how_to_use_profile,
                        requireContext().resources.getString(R.string.how_profile),
                        requireContext().resources.getString(R.string.content1_profile),
                        requireContext().resources.getString(R.string.content2_profile)

                )
        )
        howToUserList.add(
                HowToUse(
                        5,
                        R.drawable.how_to_use_qr,
                        requireContext().resources.getString(R.string.how_qr),
                        requireContext().resources.getString(R.string.content1_qr),
                        requireContext().resources.getString(R.string.content2_qr)
                )
        )
        howToUserList.add(
                HowToUse(
                        6,
                        R.drawable.how_to_use_get_itzme,
                        requireContext().resources.getString(R.string.how_use_get_itzme),
                        requireContext().resources.getString(R.string.content1_use_get_itzme),
                        requireContext().resources.getString(R.string.content2_use_get_itzme)
                )
        )
        howToUseAdapter.submitList(howToUserList)
        binding?.imageSlider?.apply {
            adapter = howToUseAdapter
            binding?.wormDotsIndicator?.setViewPager2(this)
        }
        binding?.imageSlider?.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == howToUseAdapter.itemCount - 1) {
                    binding?.nextBtn?.text = getText(R.string.activate)
                    binding?.nextBtn?.setTextColor(
                            ColorStateList.valueOf(
                                    requireContext().getColor(
                                            R.color.white
                                    )
                            )
                    )
                    binding?.nextBtn?.backgroundTintList =
                            ColorStateList.valueOf(requireContext().getColor(R.color.dark_blue))
                    binding?.skipBtn?.visibility = View.INVISIBLE
                } else {
                    binding?.nextBtn?.setTextColor(
                            ColorStateList.valueOf(
                                    requireContext().getColor(
                                            R.color.dark_blue
                                    )
                            )
                    )
                    binding?.nextBtn?.backgroundTintList =
                            ColorStateList.valueOf(requireContext().getColor(R.color.white))
                    binding?.nextBtn?.text = getText(R.string.next)
                    binding?.skipBtn?.visibility = View.VISIBLE
                }
            }
        })
    }

    //endregion

}