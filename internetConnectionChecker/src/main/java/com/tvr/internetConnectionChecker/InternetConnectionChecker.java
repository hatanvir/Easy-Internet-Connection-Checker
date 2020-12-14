package com.tvr.internetConnectionChecker;

import android.content.Context;
import android.widget.Toast;

import com.tvr.internetConnectionChecker.features.model.NetworkStatusModel;
import com.tvr.internetConnectionChecker.features.model.NetworkStatusModelImplementation;
import com.tvr.internetConnectionChecker.features.viewmodel.NetworkStatusViewmodel;

import androidx.lifecycle.Observer;

/**
 * Created by Tanvir on 14/12/20.
 */
public class InternetConnectionChecker {
        NetworkStatusModel model;
        NetworkStatusViewmodel viewmodel;

      /*  viewmodel.networkEnableDisable(true,this,model);

        viewmodel.networkStatus.observe(this, new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean aBoolean) {
            if(aBoolean){
                Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "Not connected", Toast.LENGTH_SHORT).show();
            }
        }
    });*/

        /*void startDetect(){
            model = new NetworkStatusModelImplementation(this);
            viewmodel = this.ViewModelProviders.of(this).get(NetworkStatusViewmodel.class);
        }*/

    /*public static void networkEnableDisable(boolean status, Context context){
        NetworkStatusModel model = new NetworkStatusModelImplementation(context);
        model.networkEnableDisable(status, context, new NetWorkStatusListeners<Boolean>() {
            @Override
            public void status(boolean st) {
                Toast.makeText(context, "Connected"+st, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static boolean register(Context context){
        NetworkStatusModel model = new NetworkStatusModelImplementation(context);
        model.register(context, new NetWorkStatusListeners<Boolean>() {
            @Override
            public void status(boolean st) {
                return true;
            }
        });
    }*/

    public static void register(Context context){
        NetworkStatusModel model = new NetworkStatusModelImplementation(context);
        model.register(context, new NetWorkStatusListeners<Boolean>() {
            @Override
            public void status(boolean st) {
                getConnectionStatus(st);
            }
        });
    }

    public static boolean getConnectionStatus(boolean b){
        return b;
    }
    public static void unRegister(Context context){
        NetworkStatusModel model = new NetworkStatusModelImplementation(context);
        model.unRegister(context);
    }
}
