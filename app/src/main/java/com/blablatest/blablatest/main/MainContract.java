package com.blablatest.blablatest.main;

import com.blablatest.blablatest.base.ILoadingView;
import com.blablatest.blablatest.base.IPresenter;


public class MainContract {

    interface View extends ILoadingView {

    }


    interface Presenter extends IPresenter<View> {
    }
}
