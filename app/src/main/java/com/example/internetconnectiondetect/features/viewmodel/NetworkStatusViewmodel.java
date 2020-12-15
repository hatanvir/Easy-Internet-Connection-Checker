package com.example.internetconnectiondetect.features.viewmodel;

import android.app.Activity;
import android.content.Context;

import com.example.internetconnectiondetect.features.model.NetworkStatusModel;
import com.example.internetconnectiondetect.NetWorkStatusListeners;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Tanvir on 14/12/20.
 */

public class NetworkStatusViewmodel extends ViewModel {
    public MutableLiveData<Boolean> networkStatus = new MutableLiveData<>();

    void getStatus(Activity context, NetworkStatusModel model){
        model.getStatus(context, new NetWorkStatusListeners<Boolean>() {
            @Override
            public void status(boolean st) {
                networkStatus.postValue(st);
            }
        });
    }
    void register(Context context, NetworkStatusModel model){
       model.register(context, new NetWorkStatusListeners<Boolean>() {
           @Override
           public void status(boolean st) {

           }
       });
    }
    void unRegister(Context context, NetworkStatusModel model){
        model.unRegister(context);
    }
}
