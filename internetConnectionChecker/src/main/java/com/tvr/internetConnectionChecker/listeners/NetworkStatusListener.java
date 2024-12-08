package com.tvr.internetConnectionChecker.listeners;

/**
 * Created by Tanvir on 14/12/20.
 */
public interface NetworkStatusListener {
    void connected(); //connected but not sure data is available or not
    void capable(); //connected and surely data is available
    void notConnected(); // not connected
    void notCapable(); //connected but data is not available
}
