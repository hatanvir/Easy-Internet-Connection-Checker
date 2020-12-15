package com.example.internetconnectiondetect.features.model;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.internetconnectiondetect.NetWorkStatusListeners;

import java.lang.ref.WeakReference;


/**
 * Created by Tanvir on 14/12/20.
 */

public class NetworkStatusModelImplementation extends BroadcastReceiver implements NetworkStatusModel {
     Context contexx;

    public  NetworkStatusModelImplementation(Context context) {
        contexx = context;
    }

    NetWorkStatusListeners<Boolean> netWorkStatusListeners;
   private WeakReference<NetWorkStatusListeners> mNetworkChangeListenerWeakReference;

    @Override
    public void onReceive(Context context, Intent intent) {
        netWorkStatusListeners.status(isConnected(context));
    }
    /*BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            netWorkStatusListeners.status(isConnected(context));
        }
    };*/


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
    public void getStatus(Activity activity, NetWorkStatusListeners<Boolean> netWorkStatusListeners) {
        this.netWorkStatusListeners = netWorkStatusListeners;
        contexx.registerReceiver(this,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void register(Context context,NetWorkStatusListeners<Boolean> netWorkStatusListeners) {
       this.netWorkStatusListeners = netWorkStatusListeners;
    }

    @Override
    public void unRegister(Context context) {
        //if(mNetworkChangeListenerWeakReference!=null)
        //mNetworkChangeListenerWeakReference.clear();
      // try{
           contexx.unregisterReceiver(this);
       //}catch (Exception e){}

        //contexx.un
    }
}
