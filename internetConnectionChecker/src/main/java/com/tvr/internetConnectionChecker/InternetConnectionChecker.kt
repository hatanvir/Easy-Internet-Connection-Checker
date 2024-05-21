package com.tvr.internetConnectionChecker

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log

/**
 * Created by Tanvir on 14/12/20.
 */
class InternetConnectionChecker(context: Context) : NetworkStatusModel,
    ConnectivityManager.NetworkCallback() {
    private var netWorkStatusListeners: NetWorkStatusListeners? = null
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    fun checkNetwork() = this.isConnected()
    fun checkCapableNetwork() = this.isCapable()

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

    private fun isCapable(): Boolean {
        val network = connectivityManager.activeNetwork
        if (network != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities != null
                    && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }
        return false
    }

    override fun getStatus(netWorkStatusListeners: NetWorkStatusListeners) {
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

        netWorkStatusListeners?.onConnectionNetSuccess()
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        if(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
            netWorkStatusListeners?.onConnectionCapableNetSuccess()
        }else if(!networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
            netWorkStatusListeners?.onConnectionCapableNetFailed()
        }

        Log.d("STATUS_LIB",""+networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        netWorkStatusListeners?.OnConnectionNetFailed()
    }

}