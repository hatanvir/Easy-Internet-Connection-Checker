package com.tvr.internetConnectionChecker.listeners

interface NetworkCapabilityListener {
    fun capable() //connected and surely data is available
    fun notCapable() //connected but data is not available
}