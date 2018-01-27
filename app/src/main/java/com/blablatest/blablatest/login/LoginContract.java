package com.blablatest.blablatest.login;


import com.blablatest.blablatest.base.ILoadingView;
import com.blablatest.blablatest.base.IPresenter;
import com.dd.processbutton.iml.ActionProcessButton;

/**
 * Created by Louis on 2017/5/19.
 */

public class LoginContract {

    interface View extends ILoadingView {

        public String getEmail();

        public void setCurrentState(String text);

        public String getPassword();


        public void setLoginBtMode(ActionProcessButton.Mode mode);



        public void loginSuccess();
    }


    interface Presenter extends IPresenter<View> {

        void login();

        void checkLogin();

    }
}
