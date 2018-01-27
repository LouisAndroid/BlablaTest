package com.blablatest.blablatest.map;

import com.blablatest.blablatest.base.ILoadingView;
import com.blablatest.blablatest.base.IPresenter;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Louis on 2018/1/27.
 */

public class MapContract {

    interface View extends ILoadingView {


    }


    interface Presenter extends IPresenter<MapContract.View> {
        public void addMarks(GoogleMap googleMap);
    }
}
