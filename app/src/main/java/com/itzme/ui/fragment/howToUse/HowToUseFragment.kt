package com.itzme.ui.fragment.howToUse

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.itzme.R
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
    }


    //region init Click

    private fun initClick() {
        binding?.nextBtn?.setOnClickListener {
            if (binding?.imageSlider?.currentItem!! + 1 < howToUseAdapter.itemCount) {
                binding?.imageSlider!!.currentItem += 1
            } else {

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
        howToUserList.add(HowToUse(1, R.drawable.how_to_use_iphone, "How to itz to iphone",
                "Tap your itzme to the very top to itz to an iPhone XR and newer   ",
                "Older iPhones can use the NFC widget in control center to read your itzme"))
        howToUserList.add(HowToUse(2, R.drawable.how_to_user_android,
                "How to itz to android",
                "Tap your itzme to the middle to itz to Android  ",
                "Make sure they have NFC turned on in settings! Some Androids have this off"))
        howToUserList.add(HowToUse(3,
                R.drawable.how_to_use_direct,
                "itzme Direct",
                "Tap your itzme to the middle to itz to Android  ",
                "When itzme Direct is on, your itzme will open directly to the first link on your profile"))
        howToUserList.add(HowToUse(4,
                R.drawable.how_to_use_profile,
                "Your itzme profile",
                "Turn itzme Direct off to share your full itzme Profile ",
                "Your itzme Profile is fully customizable, and links can be sorted using drag and drop"))
        howToUserList.add(HowToUse(5,
                R.drawable.how_to_use_qr,
                "your qr code",
                "Use your itzme QR Code to share with older Phone models ",
                "Your QR Code can be accessed on the top right of your profile and in the main menu"))
        howToUserList.add(HowToUse(6,
                R.drawable.how_to_use_get_itzme,
                "let’s get itzme",
                "Your view count is the number of times you share with itzme",
                "Your QR Code can be accessed on the top right of your profile and in the main menu"))
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
                    binding?.nextBtn?.setTextColor(ColorStateList.valueOf(requireContext().getColor(R.color.white)))
                    binding?.nextBtn?.backgroundTintList =
                            ColorStateList.valueOf(requireContext().getColor(R.color.dark_blue))
                    binding?.skipBtn?.visibility = View.INVISIBLE
                } else {
                    binding?.nextBtn?.setTextColor(ColorStateList.valueOf(requireContext().getColor(R.color.dark_blue)))
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