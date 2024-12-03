package com.tvr.internetConnectionChecker

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import java.net.InetSocketAddress
import java.net.Socket

/**
 * Created by Tanvir on 14/12/20.
 */
class InternetConnectionChecker(context: Context) : NetworkStatusModel,
    ConnectivityManager.NetworkCallback() {
    private var networkStatusListeners: NetworkStatusListeners? = null
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
            if (networkCapabilities != null
                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            ) {
               /* Thread {
                    try {
                        Socket().use { socket ->
                            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                            return true
                        }
                    } catch (e: Exception) {
                        return false
                    }
                }.start()*/
            }

        }
        return false
    }

    override fun getStatus(networkStatusListeners: NetworkStatusListeners) {
        this.networkStatusListeners = networkStatusListeners
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

        networkStatusListeners?.connected()
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            Thread {
                try {
                    Socket().use { socket ->
                        socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                        networkStatusListeners?.capable()
                    }
                } catch (e: Exception) {
                    networkStatusListeners?.notCapable()
                }
            }.start()
        } else if (!networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            networkStatusListeners?.notCapable()
        }

        Log.d(
            "STATUS_LIB",
            "" + networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        )
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        networkStatusListeners?.notConnected()
    }

}