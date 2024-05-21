package com.tvr.internetConnectionChecker;

/**
 * Created by Tanvir on 14/12/20.
 */
public interface NetWorkStatusListeners {
    void onConnectionNetSuccess();
    void onConnectionCapableNetSuccess();
    void onConnectionCapableNetFailed();
    void OnConnectionNetFailed();
}
