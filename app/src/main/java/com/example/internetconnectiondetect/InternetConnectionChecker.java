package com.example.internetconnectiondetect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by Tanvir on 14/12/20.
 */

public class InternetConnectionChecker extends BroadcastReceiver implements NetworkStatusModel {
     Context context;

    public InternetConnectionChecker(Context context) {
        this.context = context;
    }

    NetWorkStatusListeners<Boolean> netWorkStatusListeners;

    @Override
    public void onReceive(Context context, Intent intent) {
        netWorkStatusListeners.status(isConnected(context));
    }

    private boolean isConnected(Context context) {
        try{
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public void getStatus( NetWorkStatusListeners<Boolean> netWorkStatusListeners) {
        this.netWorkStatusListeners = netWorkStatusListeners;
        context.registerReceiver(this,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    @Override
    public void unRegister() {
       try{
           context.unregisterReceiver(this);
       }catch (Exception e){}
    }
}
