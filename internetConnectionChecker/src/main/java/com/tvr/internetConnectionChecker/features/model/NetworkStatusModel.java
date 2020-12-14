package com.tvr.internetConnectionChecker.features.model;

import android.content.Context;

import com.tvr.internetConnectionChecker.NetWorkStatusListeners;

/**
 * Created by Tanvir on 14/12/20.
 */
public interface NetworkStatusModel {
    void register(Context context, NetWorkStatusListeners<Boolean> netWorkStatusListeners);
    void unRegister( Context context);
}
