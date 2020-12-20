package com.example.internetconnectiondetect;

import android.app.Activity;
import android.content.Context;

import com.example.internetconnectiondetect.NetWorkStatusListeners;

/**
 * Created by Tanvir on 14/12/20.
 */
public interface NetworkStatusModel {
    void unRegister();
    void getStatus(NetWorkStatusListeners<Boolean> netWorkStatusListeners);
}
