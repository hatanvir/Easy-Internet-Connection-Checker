package com.tvr.internetConnectionChecker;

import android.content.Context;

/**
 * Created by Tanvir on 14/12/20.
 */
public interface NetworkStatusModel {
    void unRegister();
    void getStatus(NetworkStatusListeners netWorkStatusListeners);
}
