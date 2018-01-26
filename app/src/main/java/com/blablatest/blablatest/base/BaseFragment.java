package com.blablatest.blablatest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends IPresenter> extends DialogFragment implements IView {


    protected T mPresenter;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        setupActivityComponent();
        mPresenter = createPresenter();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEventAndData();
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();

    protected abstract T createPresenter();

    protected abstract void setupActivityComponent();
}
