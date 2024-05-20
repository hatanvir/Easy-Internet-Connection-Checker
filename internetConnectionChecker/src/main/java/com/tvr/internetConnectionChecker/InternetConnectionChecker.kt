package com.tvr.internetConnectionChecker

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

/**
 * Created by Tanvir on 14/12/20.
 */
class InternetConnectionChecker(context: Context) : NetworkStatusModel,
    ConnectivityManager.NetworkCallback() {
    private var netWorkStatusListeners: NetWorkStatusListeners<Boolean>? = null
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    fun checkNetwork() = this.isConnected()
    private fun isConnected(): Boolean {
        val network = connectivityManager.activeNetwork
        if (network != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities != null && networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            )
        }
        return false
    }

    override fun getStatus(netWorkStatusListeners: NetWorkStatusListeners<Boolean>) {
        this.netWorkStatusListeners = netWorkStatusListeners
        connectivityManager.registerDefaultNetworkCallback(this)
    }

    override fun unRegister() {
        try {
            connectivityManager.unregisterNetworkCallback(this)
        } catch (e: Exception) {
        }
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        netWorkStatusListeners?.status(isConnected())
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        netWorkStatusListeners?.status(false)
    }

}