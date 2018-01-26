package com.blablatest.blablatest.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class BasePresenter<T extends IView> implements IPresenter<T> {


    protected T view;
    protected CompositeSubscription compositeSubscription;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    protected void unSubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
            compositeSubscription = null;
        }
    }

    protected void addSubscribe(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.clear();
        compositeSubscription.add(subscription);
    }

    @Override
    public void detachView() {
        this.view = null;
        unSubscribe();
    }
}
