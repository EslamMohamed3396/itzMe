package com.itzme.utilits.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class NetworkUtils(context: Context) : LiveData<Boolean>() {


    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private val validNetworks: MutableSet<Network> = HashSet()

    private fun checkValidNetworks() {
        postValue(validNetworks.size > 0)
    }

    override fun onActive() {
        networkCallback = createNetworkCallback()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .build()
        cm.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onInactive() {
        cm.unregisterNetworkCallback(networkCallback)
    }

    private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {

        /*
          Called when a network is detected. If that network has internet, save it in the Set.
          Source: https://developer.android.com/reference/android/net/ConnectivityManager.NetworkCallback#onAvailable(android.net.Network)
         */
        override fun onAvailable(network: Network) {
            Timber.d("onAvailable: $network")
            val networkCapabilities = cm.getNetworkCapabilities(network)
            val hasInternetCapability = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
            Timber.d("onAvailable: ${network}, $hasInternetCapability")
            if (hasInternetCapability == true) {
                // check if this network actually has internet
                CoroutineScope(Dispatchers.IO).launch {
                    val hasInternet = DoesNetworkHaveInternet.execute(network.socketFactory)
                    if (hasInternet) {
                        withContext(Dispatchers.Main) {
                            Timber.d("onAvailable: adding network. $network")
                            validNetworks.add(network)
                            checkValidNetworks()
                        }
                    }
                }
            }
        }

        /*
          If the callback was registered with registerNetworkCallback() it will be called for each network which no longer satisfies the criteria of the callback.
          Source: https://developer.android.com/reference/android/net/ConnectivityManager.NetworkCallback#onLost(android.net.Network)
         */
        override fun onLost(network: Network) {
            Timber.d("onLost: $network")
            validNetworks.remove(network)
            checkValidNetworks()
        }

    }

}

//(private val context: Context) : LiveData<Boolean>() {
//    private var connectivityManager: ConnectivityManager =
//        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    private val TAG = "NetworkUtils"
//    private var networkCallback: ConnectivityManager.NetworkCallback? = null
//
//    override fun onActive() {
//        super.onActive()
//        updateConnection()
//
//        when {
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
//                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback())
//            }
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
//                lollipopNetworkRequest()
//            }
//            else -> {
//                context.registerReceiver(
//                    networkReceiver,
//                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//                )
//            }
//
//        }
//    }
//
//    public override fun onInactive() {
//        super.onInactive()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            connectivityManager.unregisterNetworkCallback(connectivityManagerCallback())
//        } else {
//            context.unregisterReceiver(networkReceiver)
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private fun lollipopNetworkRequest() {
//        val requestBuilder = NetworkRequest.Builder()
//            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
//        connectivityManager.registerNetworkCallback(
//            requestBuilder.build(),
//            connectivityManagerCallback()
//        )
//    }
//
//    private fun connectivityManagerCallback(): ConnectivityManager.NetworkCallback {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            if (networkCallback == null) {
//                networkCallback = object : ConnectivityManager.NetworkCallback() {
//                    override fun onLost(network: Network) {
//                        super.onLost(network)
//                        postValue(false)
//                    }
//
//                }
//            }
//            return networkCallback as ConnectivityManager.NetworkCallback
//        } else {
//            throw  IllegalAccessError("Error")
//        }
//    }
//
//    private val networkReceiver = object : BroadcastReceiver() {
//        override fun onReceive(p0: Context?, p1: Intent?) {
//            updateConnection()
//        }
//    }
//
//    private fun updateConnection() {
//        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//        postValue(activeNetwork?.isConnected == true)
//    }
//
//}