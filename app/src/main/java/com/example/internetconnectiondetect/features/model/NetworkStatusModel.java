package com.example.internetconnectiondetect.features.model;

import android.app.Activity;
import android.content.Context;

import com.example.internetconnectiondetect.NetWorkStatusListeners;

/**
 * Created by Tanvir on 14/12/20.
 */
public interface NetworkStatusModel {
    void register(Context context,NetWorkStatusListeners<Boolean> netWorkStatusListeners);
    void unRegister( Context context);
    void getStatus(Activity activity, NetWorkStatusListeners<Boolean> netWorkStatusListeners);
}
