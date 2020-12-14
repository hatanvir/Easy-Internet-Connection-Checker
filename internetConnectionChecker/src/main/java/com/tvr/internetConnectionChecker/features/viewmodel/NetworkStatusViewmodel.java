package com.tvr.internetConnectionChecker.features.viewmodel;

import android.content.Context;

import com.tvr.internetConnectionChecker.NetWorkStatusListeners;
import com.tvr.internetConnectionChecker.features.model.NetworkStatusModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Tanvir on 14/12/20.
 */
public class NetworkStatusViewmodel extends ViewModel {
    public MutableLiveData<Boolean> networkStatus = new MutableLiveData<>();

    /*public void networkEnableDisable(boolean status,Context context,NetworkStatusModel model){
        model.networkEnableDisable(status, context, new NetWorkStatusListeners<Boolean>() {
            @Override
            public void status(boolean st) {
                networkStatus.postValue(st);
            }
        });
    }*/

    static void register(Context context, NetworkStatusModel model){
        /*model.register(context, new NetWorkStatusListeners<Boolean>() {
            @Override
            public void status(boolean st) {
                 networkStatus.postValue(st);
            }
        });*/
    }

    void unRegister(Context context, NetworkStatusModel model){
        model.unRegister(context);
    }
}
