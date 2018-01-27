package com.blablatest.blablatest.login;

import com.blablatest.blablatest.api.client.BlablaClient;
import com.blablatest.blablatest.base.BasePresenter;
import com.blablatest.blablatest.entity.CurrentState;
import com.blablatest.blablatest.entity.UserLogin;
import com.dd.processbutton.iml.ActionProcessButton;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Louis on 2017/5/19.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private BlablaClient blablaClient;


    public LoginPresenter(ClearableCookieJar clearableCookieJar) {
        blablaClient = new BlablaClient(clearableCookieJar);
    }

    @Override
    public void login() {
        view.setLoginBtMode(ActionProcessButton.Mode.PROGRESS);
        blablaClient.login(view.getEmail(), view.getPassword())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        view.setLoginBtMode(ActionProcessButton.Mode.ENDLESS);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserLogin>() {
                    @Override
                    public void call(UserLogin userLogin) {
                        if (userLogin.isLogin()) {
                            view.showMessage("登入成功");
                            view.loginSuccess();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    @Override
    public void checkLogin() {
        blablaClient.getLoginCurrentState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CurrentState>() {
                    @Override
                    public void call(CurrentState currentState) {
                        if (currentState.getUserId() != null && !currentState.getUserId().isEmpty()) {
                            view.setCurrentState("已登入_" + "登入帳號為：" + currentState.getUserId());
                        }else{
                            view.setCurrentState("已登出");

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }
}
