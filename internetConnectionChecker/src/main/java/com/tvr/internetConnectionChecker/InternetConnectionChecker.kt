package com.tvr.internetConnectionChecker

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.tvr.internetConnectionChecker.listeners.NetworkStatusListener
import com.tvr.internetConnectionChecker.listeners.NetworkStatusModel
import java.lang.ref.WeakReference
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Semaphore
import kotlin.concurrent.thread

/**
 * Created by Tanvir on 14/12/20.
 */
class InternetConnectionChecker(context: Context) : NetworkStatusModel,
    ConnectivityManager.NetworkCallback() {
    private var contextWeakReference = WeakReference(context)
    private var networkStatusListener: NetworkStatusListener? = null

    private val connectivityManager =
        contextWeakReference.get()
            ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun getStatus(networkStatusListeners: NetworkStatusListener) {
        this.networkStatusListener = networkStatusListeners
        connectivityManager.registerDefaultNetworkCallback(this)
    }

    fun isConnected(): Boolean {
        val network = connectivityManager.activeNetwork
        if (network != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities != null && networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            )
        }
        return false
    }

    fun isCapable(): Boolean {
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return if (networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) == true) {
            try {
                val isCapable = checkCapability()
                isCapable
            } catch (e: Exception) {
                false
            }

        } else if (networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) != true) {
            false
        } else false
    }

    private fun checkCapability(): Boolean {
        val semaphore = Semaphore(0)
        var status = false

        thread {
            try {
                Socket().use { socket ->
                    socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)//googles public DNS
                    status = true
                }
            } catch (_: Exception) {
                status = false
            }
            semaphore.release()
        }

        semaphore.acquire()
        return status

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
        //super.onCapabilitiesChanged(network, networkCapabilities)
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            try {
                val isCapable = isCapable()
                if (isCapable) networkStatusListener?.capable()
                else networkStatusListener?.notCapable()
            } catch (e: Exception) {
                networkStatusListener?.notCapable()
            }

        } else if (!networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            networkStatusListener?.notCapable()
        }
    }


    /**
     * will call if connection is lost
     */
    override fun onLost(network: Network) {
        super.onLost(network)
        networkStatusListener?.notConnected()
    }

}