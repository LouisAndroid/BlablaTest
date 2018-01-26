package com.blablatest.blablatest.base;


public interface IPresenter<T extends IView> {

    void attachView(T view);

    void detachView();
}
