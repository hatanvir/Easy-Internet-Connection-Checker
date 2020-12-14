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
public class NetworkStatusModelImplementation extends BroadcastReceiver implements NetworkStatusModel{
    Context context;

    public NetworkStatusModelImplementation(Context context) {
        this.context = context;
    }

    NetWorkStatusListeners<Boolean> netWorkStatusListeners;

    @Override
    public void networkEnableDisable(boolean status, Context context, NetWorkStatusListeners<Boolean> netWorkStatusListeners) {
        if(status){
            this.netWorkStatusListeners = netWorkStatusListeners;
            context.registerReceiver(this,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }else {
            this.netWorkStatusListeners = netWorkStatusListeners;
            context.unregisterReceiver(this);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(isConnected(context)){
            netWorkStatusListeners.status(true);
        }else {
            netWorkStatusListeners.status(false);
            //status.setValue(false);
            Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
            // Utils.setStatus(false);
            bb = false;
        }
    }


    boolean bb;
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
}
