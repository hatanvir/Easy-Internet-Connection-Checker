package com.tvr.internetConnectionChecker.features.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.tvr.internetConnectionChecker.NetWorkStatusListeners;

/**
 * Created by Tanvir on 14/12/20.
 */
public class NetworkStatusModelImplementation implements NetworkStatusModel{
     Context context;

    public  NetworkStatusModelImplementation(Context context) {
        this.context = context;
    }

    NetWorkStatusListeners<Boolean> netWorkStatusListeners;

    /*@Override
    public void onReceive(Context context, Intent intent) {
        if(isConnected(context)){
            netWorkStatusListeners.status(true);
        }else {
            netWorkStatusListeners.status(false);
        }
    }*/
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(isConnected(context)){
                netWorkStatusListeners.status(true);
            }else {
                netWorkStatusListeners.status(false);
            }
        }
    };


    private boolean isConnected(Context context) {
        try{
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

            if(activeNetwork!=null && activeNetwork.isConnectedOrConnecting()){
                return  true;
            }
            return  false;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public void register(Context context, NetWorkStatusListeners<Boolean> netWorkStatusListeners) {
        this.netWorkStatusListeners = netWorkStatusListeners;
        context.registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void unRegister(Context context) {
       try{
           context.unregisterReceiver(broadcastReceiver);
       }catch (Exception e){}
    }
}
