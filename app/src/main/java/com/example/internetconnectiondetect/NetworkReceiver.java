package com.example.internetconnectiondetect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Tanvir on 15/12/20.
 */
class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(isConnected(context)){
            Toast.makeText(context, "cc"+true, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "cc"+false, Toast.LENGTH_SHORT).show();
        }
    }


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
