package com.puneet.tunein_navigation;

import android.app.Application;

import com.puneet.tunein_navigation.network.RetrofitManager;

public class TuneInDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.sInstance.init(TuneInDemoApplication.this);
    }

}
