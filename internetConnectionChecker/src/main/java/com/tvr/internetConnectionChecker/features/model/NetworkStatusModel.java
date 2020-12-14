package com.tvr.internetConnectionChecker.features.model;

import android.content.Context;

import com.tvr.internetConnectionChecker.NetWorkStatusListeners;

/**
 * Created by Tanvir on 14/12/20.
 */
public interface NetworkStatusModel {
    void networkEnableDisable(boolean status,Context context, NetWorkStatusListeners<Boolean> netWorkStatusListeners);
}
