package com.tvr.internetConnectionChecker;

/**
 * Created by Tanvir on 14/12/20.
 */
public interface NetworkStatusListeners {
    void connected();
    void capable();
    void notConnected();
    void notCapable();
}
