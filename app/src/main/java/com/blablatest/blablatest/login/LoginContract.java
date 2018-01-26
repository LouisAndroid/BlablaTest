package com.blablatest.blablatest.login;


import com.blablatest.blablatest.base.ILoadingView;
import com.blablatest.blablatest.base.IPresenter;

/**
 * Created by Louis on 2017/5/19.
 */

public class LoginContract {

    interface View extends ILoadingView {

    }


    interface Presenter extends IPresenter<View> {

    }
}
