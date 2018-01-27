package com.blablatest.blablatest.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Louis on 2018/1/27.
 */

public class UserLogin {

    @SerializedName("result")
    private boolean isLogin;

    @SerializedName("user_id")
    private String userId;

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isLogin() {
        return isLogin;
    }
}
