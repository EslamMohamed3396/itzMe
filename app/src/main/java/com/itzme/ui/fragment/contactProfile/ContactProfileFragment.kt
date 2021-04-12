package com.itzme.ui.fragment.contactProfile

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.itzme.R
import com.itzme.data.models.contact.contactProfile.response.Data
import com.itzme.data.models.contact.contactProfile.response.MyLink
import com.itzme.databinding.FragmentContactProfileBinding
import com.itzme.ui.activity.main.ReadTagViewModel
import com.itzme.ui.base.BaseFragment
import com.itzme.ui.base.IClickOnItems
import com.itzme.utilits.DialogUtil
import com.itzme.utilits.Resource


class ContactProfileFragment : BaseFragment<FragmentContactProfileBinding>(), IClickOnItems<MyLink> {

    private val myLinkAdapter: LinkProfileAdapter by lazy { LinkProfileAdapter(this) }
    private val viewModel: ContactProfileViewModel by viewModels()
    private val args: ContactProfileFragmentArgs by navArgs()
    private val viewModelReadTag: ReadTagViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, R.layout.fragment_contact_profile)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.toolbar?.titleTv?.text = requireContext().resources.getString(R.string.profile)
        initClick()
        if (args.userNameArgs != null) {
            initReadViewModel(args.userNameArgs!!)
        } else {
            initContactProfile(args.contactIdArgs)
        }
    }


    private fun initClick() {
        binding?.toolbar?.toolbarImg?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    //region bindData

    private fun bindAdapter() {
        binding?.link = myLinkAdapter
    }

    private fun bindMyProfile(myProfile: Data) {
        binding?.myProfile = myProfile
    }

    //endregion

    //region init view model

    private fun initContactProfile(contactId: Int) {
        bindAdapter()
        viewModel.contactProfile(contactId).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {

                    DialogUtil.dismissDialog()

                    bindMyProfile(response.data?.data!!)
                    if (response.data.data.myLinks?.isNotEmpty()!!) {
                        myLinkAdapter.submitList(response.data.data.myLinks)
                    } else {
                        binding?.tvEmpty?.visibility = View.VISIBLE
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

    private fun initReadViewModel(userName: String) {
        viewModelReadTag.readTag(userName, "").observe(viewLifecycleOwner, { response ->
            val message = response.data?.errorMessage
            when (response) {
                is Resource.Loading -> {
                    // DialogUtil.showDialog(requireContext())
                }
                is Resource.Success -> {
                    // DialogUtil.dismissDialog()
                    if (response.data?.errorCode == 0) {
                        initContactProfile(response.data.data!!)
                    } else {
                        val action = ContactProfileFragmentDirections.actionContactProfileFragmentToMyProfileFragment()
                        findNavController().navigate(action)
                    }
                }
                is Resource.Error -> {
                    //  DialogUtil.dismissDialog()
                    when (response.code) {
                        401 -> {

                        }
                        else -> {
                            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }
        })

    }

    //endregion

    override fun clickOnItems(item: MyLink, postion: Int) {
        when (item.linkType) {
            in 0..10, in 22..41 -> {
                openLink(item.link!!)
            }
            11, 12 -> {
                openEmail(item.link!!)
            }
            13 -> {
                openLine(item.link!!)
            }
            14, 15 -> {
                openPhone(item.link!!)
            }
            16, 17 -> {
                openWhatsApp(item.link!!)
            }
            18 -> {
                intentMessageTelegram(item.link!!)
            }
            19, 20, 21 -> {
                val action = ContactProfileFragmentDirections.actionContactProfileFragmentToAddLinkSheetContact(item)
                findNavController().navigate(action)
            }
            42 -> {
                val action = ContactProfileFragmentDirections.actionContactProfileFragmentToPetSheetContact(item)
                findNavController().navigate(action)
            }
            43 -> {
                val action = ContactProfileFragmentDirections.actionContactProfileFragmentToFindMeSheetContact(item)
                findNavController().navigate(action)
            }
        }
    }

    //region open another apps


    private fun openWhatsApp(number: String) {
        val url = "https://api.whatsapp.com/send?phone=$number"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun openPhone(number: String) {
        val u = Uri.parse("tel:" + number)


        val i = Intent(Intent.ACTION_DIAL, u)
        try {

            startActivity(i)
        } catch (s: SecurityException) {
        }
    }

    private fun intentMessageTelegram(msg: String?) {
        val appName = "org.telegram.messenger"
        val isAppInstalled: Boolean = isAppAvailable(requireContext().applicationContext, appName)
        if (isAppInstalled) {
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "text/plain"
            myIntent.setPackage(appName)
            myIntent.putExtra(Intent.EXTRA_TEXT, msg) //
            startActivity(Intent.createChooser(myIntent, "Share with"))
        } else {
            Toast.makeText(requireContext(), "Telegram not Installed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url) // only used based on your example.


        val title = "Select a browser"

        val chooser = Intent.createChooser(intent, title)

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(chooser)
        }
    }


    fun isAppAvailable(context: Context, appName: String?): Boolean {
        val pm: PackageManager = context.packageManager
        return try {
            pm.getPackageInfo(appName!!, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }


    private fun openWebPage(url: String?) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun openEmail(url: String?) {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", url, null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }

    private fun openLine(url: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("https://line.me/R/oaMessage/$url/?Hi")
        startActivity(intent)
    }

    //endregion


}