package com.blablatest.blablatest.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Louis on 2018/1/27.
 */

public class CurrentState {

    @SerializedName("user_id")
    private String userId;

    private String reason;

    @SerializedName("result")
    private boolean isLogin;


    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public String getReason() {
        return reason;
    }
}
