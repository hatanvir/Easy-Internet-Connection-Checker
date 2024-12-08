package com.tvr.internetConnectionChecker.listeners;

/**
 * Created by Tanvir on 14/12/20.
 */
public interface NetworkStatusModel {
    void unRegister();
    void getStatus(NetworkStatusListener netWorkStatusListeners);
}
