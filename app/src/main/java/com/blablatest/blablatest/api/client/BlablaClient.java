package com.blablatest.blablatest.api.client;

import com.blablatest.blablatest.api.ApiConfig;
import com.blablatest.blablatest.api.service.BlablaService;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Louis on 2018/1/27.
 */

public class BlablaClient {

    private Retrofit retrofit;
    private BlablaService blablaService;

    public BlablaClient() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

        blablaService = retrofit.create(BlablaService.class);

    }

}


