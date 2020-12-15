package com.example.internetconnectiondetect;

import android.content.Context;

import com.example.internetconnectiondetect.features.model.NetworkStatusModel;
import com.example.internetconnectiondetect.features.model.NetworkStatusModelImplementation;

/**
 * Created by Tanvir on 14/12/20.
 */
public class InternetConnectionChecker {
        NetworkStatusModel model;

   /* public static NetworkStatusModelImplementation getRegister(Context context){

        return new NetworkStatusModelImplementation(context);*//*.register(context, new NetWorkStatusListeners<Boolean>() {
            @Override
            public void status(boolean st) {
                getConnectionStatus(st);
                Toast.makeText(context, ""+st, Toast.LENGTH_SHORT).show();
            }
        });*//*
    }*/

    public static NetworkStatusModelImplementation getRegister(Context context){
        return new NetworkStatusModelImplementation(context);
    }

    public static NetworkStatusModelImplementation unRegister(Context context){
        return new NetworkStatusModelImplementation(context);
        //model.unRegister(context);
    }

    public static NetworkStatusModelImplementation status(Context context){
        return new NetworkStatusModelImplementation(context);
    }

}
