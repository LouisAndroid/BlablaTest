package com.blablatest.blablatest.api.client;

import android.util.Log;

import com.blablatest.blablatest.api.ApiConfig;
import com.blablatest.blablatest.api.service.BlablaService;
import com.blablatest.blablatest.entity.CurrentState;
import com.blablatest.blablatest.entity.MapPin;
import com.blablatest.blablatest.entity.UserLogin;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Louis on 2018/1/27.
 */

public class BlablaClient {

    private Retrofit retrofit;
    private BlablaService blablaService;

    public BlablaClient(ClearableCookieJar cookieJar) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Interceptors
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                // 在 DMApplication 設定好 Logger 是否能 Log，這裡只要用就好。
                Log.d("BlablaClient", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(loggingInterceptor);
        builder.cookieJar(cookieJar);
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

        blablaService = retrofit.create(BlablaService.class);

    }

    public Observable<CurrentState> getLoginCurrentState() {
        return blablaService.getLoginState()
                .subscribeOn(Schedulers.io());
    }

    public Observable<UserLogin> login(String email, String password) {
        JSONObject object = new JSONObject();
        try {
            object.put("username", email);
            object.put("password", password);
        } catch (JSONException e) {
            return Observable.error(e);
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), object.toString());
        return blablaService.login(body)
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<MapPin>> getMapPins() {
        return blablaService.getMapPins()
                .subscribeOn(Schedulers.io());
    }


}


