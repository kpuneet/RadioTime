package com.puneet.tunein_navigation.network;

import android.app.Application;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    public static RetrofitManager sInstance = new RetrofitManager();
    private OkHttpClient httpClient;
    public static int API_TIMEOUT = 20000;
    private static String RENDER = "render";
    private static String JSON = "json";

    public void init(final Application context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.dispatcher(new Dispatcher(Executors.newCachedThreadPool()));
        builder.connectTimeout(API_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(API_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.writeTimeout(API_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.retryOnConnectionFailure(true);
        builder.followRedirects(true);
        builder.followSslRedirects(true);
        int cacheSize = 600 * 1024 * 1024; // 300 MiB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        builder.cache(cache);

        builder.addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder requestBuilder = request.newBuilder();
                HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
                httpUrlBuilder.addQueryParameter(RENDER, JSON);
                requestBuilder.url(httpUrlBuilder.build());
                Response response;
                try {
                    response = chain.proceed(requestBuilder.build());
                } catch (NullPointerException e) {
                    Log.d(RetrofitManager.class.getSimpleName(), e.getLocalizedMessage(), e);
                    response = chain.proceed(requestBuilder.cacheControl(CacheControl.FORCE_NETWORK).build());
                } catch (IllegalStateException e) {
                    Log.d(RetrofitManager.class.getSimpleName(), e.getLocalizedMessage(), e);
                    throw new IOException();
                }
                Log.d(RetrofitManager.class.getSimpleName(), "Network Response Code: " + response.code());
                return response;
            }
        });

        httpClient = builder.build();
    }

    public Retrofit getClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                /*.client(httpClient)*/
                .build();
    }
}
