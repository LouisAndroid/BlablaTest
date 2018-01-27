package com.blablatest.blablatest.map;

import com.blablatest.blablatest.api.client.BlablaClient;
import com.blablatest.blablatest.base.BasePresenter;
import com.blablatest.blablatest.entity.MapPin;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Louis on 2018/1/27.
 */

public class MapPresenter extends BasePresenter<MapContract.View> implements MapContract.Presenter {


    private BlablaClient blablaClient;

    public MapPresenter(ClearableCookieJar cookieJar) {
        blablaClient = new BlablaClient(cookieJar);
    }


    @Override
    public void addMarks(final GoogleMap googleMap) {
        blablaClient.getMapPins()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<MapPin>>() {
                    @Override
                    public void call(List<MapPin> mapPins) {
                        view.showMessage("拉到" + mapPins.size() + "筆資料");
                        for (MapPin mapPin : mapPins) {
                            LatLng sydney = new LatLng(mapPin.getY_axis(), mapPin.getX_axis());
                            googleMap.addMarker(new MarkerOptions().position(sydney).title(mapPin.getName()));
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });


    }
}
