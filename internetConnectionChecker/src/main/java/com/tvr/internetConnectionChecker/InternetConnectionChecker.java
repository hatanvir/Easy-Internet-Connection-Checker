package com.tvr.internetConnectionChecker;

import android.content.Context;
import android.widget.Toast;

import com.tvr.internetConnectionChecker.features.model.NetworkStatusModel;
import com.tvr.internetConnectionChecker.features.model.NetworkStatusModelImplementation;
import com.tvr.internetConnectionChecker.features.viewmodel.NetworkStatusViewmodel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by Tanvir on 14/12/20.
 */
public class InternetConnectionChecker {
        NetworkStatusModel model;
    public static NetworkStatusModelImplementation getRegister(Context context){
        return new NetworkStatusModelImplementation(context);
    }

    public static void unRegister(Context context){
        NetworkStatusModel model = new NetworkStatusModelImplementation(context);
        model.unRegister(context);
    }
}
