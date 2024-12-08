package com.tvr.internetConnectionChecker

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import com.tvr.internetConnectionChecker.listeners.NetworkCapabilityListener
import com.tvr.internetConnectionChecker.listeners.NetworkConnectivityListener
import com.tvr.internetConnectionChecker.listeners.NetworkStatusListener
import com.tvr.internetConnectionChecker.listeners.NetworkStatusModel
import java.lang.ref.WeakReference
import java.net.InetSocketAddress
import java.net.Socket

/**
 * Created by Tanvir on 14/12/20.
 */
class InternetConnectionChecker(context: Context) : NetworkStatusModel,
    ConnectivityManager.NetworkCallback() {
    private var contextWeakReference = WeakReference(context)
    private var networkStatusListener: NetworkStatusListener? = null

    private val connectivityManager =
        contextWeakReference.get()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun getStatus(networkStatusListeners: NetworkStatusListener) {
        this.networkStatusListener = networkStatusListeners
        connectivityManager.registerDefaultNetworkCallback(this)
    }

    /**
     * for unregistering callback
     */
    override fun unRegister() {
        try {
            connectivityManager.unregisterNetworkCallback(this)
        } catch (_: Exception) {
        }
    }

    /**
     * will call if connected
     */
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        networkStatusListener?.connected()
    }

    /**
     * will call if capability change
     */
    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            Thread {
                try {
                    Socket().use { socket ->
                        socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)//googles public DNS
                        networkStatusListener?.capable()
                    }
                } catch (e: Exception) {
                    networkStatusListener?.notCapable()
                }
            }.start()
        } else if (!networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            networkStatusListener?.notCapable()
        }

        Log.d(
            "STATUS",
            "" + networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        )
    }


    /**
     * will call if connection is lost
     */
    override fun onLost(network: Network) {
        super.onLost(network)
        networkStatusListener?.notConnected()
    }

}