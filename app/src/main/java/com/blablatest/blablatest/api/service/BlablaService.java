package com.blablatest.blablatest.api.service;

import com.blablatest.blablatest.entity.CurrentState;
import com.blablatest.blablatest.entity.MapPin;
import com.blablatest.blablatest.entity.UserLogin;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Louis on 2018/1/27.
 */

public interface BlablaService {

    @POST("/user/login")
    Observable<UserLogin> login(@Body RequestBody body);

    @GET("/user/current")
    Observable<CurrentState> getLoginState();

    @GET("/getnc/pin/user")
    Observable<List<MapPin>> getMapPins();

}
