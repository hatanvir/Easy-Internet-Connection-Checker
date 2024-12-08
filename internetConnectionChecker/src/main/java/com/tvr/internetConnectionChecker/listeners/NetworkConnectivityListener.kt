package com.tvr.internetConnectionChecker.listeners

interface NetworkConnectivityListener {
    fun connected() //connected but not sure data is available or not
    fun notConnected() // not connected
}