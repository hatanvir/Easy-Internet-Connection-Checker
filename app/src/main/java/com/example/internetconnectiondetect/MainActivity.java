package com.example.internetconnectiondetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;
    NetworkReceiver networkReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         /*broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };*/

     /*InternetConnectionChecker.getRegister(this)
             .register(this, new NetWorkStatusListeners<Boolean>() {
                 @Override
                 public void status(boolean st) {
                     Toast.makeText(MainActivity.this, ""+st, Toast.LENGTH_SHORT).show();
                 }
             });*/

     InternetConnectionChecker.status(this)
             .getStatus(this, new NetWorkStatusListeners<Boolean>() {
         @Override
         public void status(boolean st) {
             Toast.makeText(MainActivity.this, "tt"+st, Toast.LENGTH_SHORT).show();
         }
     });

        // networkReceiver = new NetworkReceiver();
        //registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
        //InternetConnectionChecker.unRegister(this);
    }*/

    @Override
    protected void onStop() {
        super.onStop();
       // unregisterReceiver(networkReceiver);
        InternetConnectionChecker.unRegister(this).unRegister(this);
    }
    /* private boolean isConnected(Context context) {
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
    }*/
}