package com.puneet.tunein_navigation;

import android.app.Application;
import android.util.Log;

import com.puneet.tunein_navigation.network.OkHttpDownloader;
import com.puneet.tunein_navigation.network.RetrofitManager;
import com.squareup.picasso.Cache;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executors;

public class TuneInDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RetrofitManager.sInstance.init(TuneInDemoApplication.this);

        try {
            Picasso.Builder builder = new Picasso.Builder(TuneInDemoApplication.this);
            builder.executor(Executors.newFixedThreadPool(4));
            builder.downloader(new OkHttpDownloader(this));
            builder.listener((picasso, uri, exception) -> Log.d(TuneInDemoApplication.class.getSimpleName(), exception.getLocalizedMessage()));
            builder.memoryCache(Cache.NONE);
            Picasso built = builder.build();
            built.setLoggingEnabled(BuildConfig.DEBUG);
            //built.setIndicatorsEnabled(BuildConfig.DEBUG);
            Picasso.setSingletonInstance(built);
        } catch (Throwable e) {
            Log.d(TuneInDemoApplication.class.getSimpleName(), e.getMessage(), e.getCause());
        }
    }

}
