package com.itzme.ui.activity.main

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.*
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.itzme.R
import com.itzme.data.models.stateNfc.enmReadWriteItzME.ReadWriteNFC
import com.itzme.databinding.ActivityMainBinding
import com.itzme.ui.SharedViewModel
import com.itzme.utilits.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var navHostFragment: NavHostFragment

    private val viewModelValidateTag: ValidateSerialViewModel by viewModels()
    private val viewModelReadTag: ReadTagViewModel by viewModels()

    private var nfcAdapter: NfcAdapter? = null
    private var nfcPendingIntent: PendingIntent? = null
    private var readWriteNFC: ReadWriteNFC? = null
    private var tag: Tag? = null
    private var intentReadTag: Intent? = null
    private val filters = arrayOf(IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED))

    private val techTypes = arrayOf(
        arrayOf(IsoDep::class.java.name),
        arrayOf(NfcA::class.java.name),
        arrayOf(NfcB::class.java.name),
        arrayOf(NfcF::class.java.name),
        arrayOf(NfcV::class.java.name),
        arrayOf(Ndef::class.java.name),
        arrayOf(NdefFormatable::class.java.name),
        arrayOf(MifareClassic::class.java.name),
        arrayOf(MifareUltralight::class.java.name)
    )

    private var tagId: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initViews()
        initSharedViewModel()

    }


    override fun onResume() {
        super.onResume()
        filters[0].addDataType("text/plain")
        nfcAdapter?.enableForegroundDispatch(
            this, nfcPendingIntent, filters, techTypes
        )
    }


    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LanguageUtils.onAttach(newBase!!))
    }

    //region init


    private fun initNfc() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        nfcPendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )
        if (nfcAdapter == null) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage(resources.getString(R.string.no_nfc))
            builder.setPositiveButton(resources.getString(R.string.cancel), null)
            val myDialog = builder.create()
            myDialog.setCanceledOnTouchOutside(false)
            myDialog.show()
        } else if (!nfcAdapter!!.isEnabled) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle(resources.getString(R.string.nfc_disabled))
            builder.setMessage(resources.getString(R.string.enable_nfc))
            builder.setPositiveButton(resources.getString(R.string.settings)) { _, _ ->
                startActivity(
                    Intent(Settings.ACTION_NFC_SETTINGS)
                )
            }
            builder.setNegativeButton(resources.getString(R.string.cancel), null)
            val myDialog = builder.create()
            myDialog.setCanceledOnTouchOutside(false)
            myDialog.show()
        }
    }


    private fun initSharedViewModel() {
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        sharedViewModel.stateNfc.observe(this, { stateNfc ->
            if (stateNfc.notify) {
                readWriteNFC = stateNfc.readWrite
                initNfc()
            }
        })
    }

    private fun initViews() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
            ?: return
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this

    }

    //endregion


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intentReadTag = intent
        tagId = intent?.getByteArrayExtra(NfcAdapter.EXTRA_ID)
        tag = intent?.getParcelableExtra(NfcAdapter.EXTRA_TAG) ?: return

        Timber.d("${byteArrayToHex(tagId!!)}")

        initValidateViewModel(byteArrayToHex(tagId!!))


    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
    }

    //region convert byte to hex string

    fun byteArrayToHex(a: ByteArray): String {
        val sb = StringBuilder(a.size * 2)
        for (b in a) sb.append(String.format("%02x:", b))
        return sb.reversed().substring(1).reversed()
    }

    //endregion


    //region init view model

    private fun initValidateViewModel(serial: String) {
        viewModelValidateTag.validateSerial(serial).observe(this, { response ->
            val message = response.data?.errorMessage
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(this)
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    if (readWriteNFC == ReadWriteNFC.WRITE_NFC) {
                        writeInNFC(tag!!)
                    } else {
                        readFromTag(intentReadTag!!)
                    }
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        401 -> {

                        }
                        else -> {
                            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }
        })

    }

    private fun initReadViewModel(userName: String, serial: String) {
        viewModelReadTag.readTag(userName, serial).observe(this, { response ->
            val message = response.data?.errorMessage
            when (response) {
                is Resource.Loading -> {
                    DialogUtil.showDialog(this)
                }
                is Resource.Success -> {
                    DialogUtil.dismissDialog()
                    sharedViewModel.saveDismissed(true)
                    Toast.makeText(
                        this,
                        resources.getString(R.string.reading_success),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Resource.Error -> {
                    DialogUtil.dismissDialog()
                    when (response.code) {
                        401 -> {

                        }
                        else -> {
                            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }
        })

    }

    //endregion


    //region write in nfc
    private fun writeInNFC(tag: Tag) {


        val ndef = Ndef.get(tag) ?: return
        Timber.d("${ndef.isWritable}")
        if (ndef.isWritable) {
            val message = NdefMessage(
                NdefRecord.createTextRecord(
                    "en",
                    PreferencesUtils(this).getInstance()
                        ?.getUserData(Constant.USER_DATA_KEY)?.linkName!!
                ),
            )


            try {
                ndef.connect()
                try {
                    ndef.writeNdefMessage(message)
                    Toast.makeText(applicationContext, "Successfully!", Toast.LENGTH_SHORT)
                        .show()
                } catch (e: Exception) {
                    // let the user know the tag refused to format
                    Timber.d("writeInNFC 1 ${e.message}")
                }
            } catch (e: Exception) {
                // let the user know the tag refused to connect
                Timber.d("writeInNFC 2 ${e.message}")
            } finally {
                ndef.close()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "There is an error please try again",
                Toast.LENGTH_SHORT
            )
                .show()
        }

    }

    //endregion


    //region read nfc

    fun readFromTag(intent: Intent) {
        val ndef = Ndef.get(tag)
        try {
            ndef.connect()

            val messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            if (messages != null) {
                val ndefMessages = arrayOfNulls<NdefMessage>(messages.size)
                for (i in messages.indices) {
                    ndefMessages[i] = messages[i] as NdefMessage
                }
                val record = ndefMessages[0]!!.records[0]
                val payload = record.payload
                val text = String(payload)
                initReadViewModel(splitLink(text), byteArrayToHex(tagId!!))
                ndef.close()
            }
        } catch (e: java.lang.Exception) {
            ndef.close()
            Toast.makeText(applicationContext, "Cannot Read From Tag.", Toast.LENGTH_LONG).show()
        }

    }
    //endregion

    //region split linkName to get Name

    private fun splitLink(link: String): String {
        val name = link.split("u/")
        return name[1]
    }

    //endregion

}