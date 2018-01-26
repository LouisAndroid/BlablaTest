package com.blablatest.blablatest.base;

public interface ILoadingView extends IView {
    void showLoading();

    void completeLoading();

    void showMessage(String msg);
}
