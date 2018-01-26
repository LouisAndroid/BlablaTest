package com.blablatest.blablatest.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.blablatest.blablatest.MainActivity;
import com.blablatest.blablatest.R;
import com.blablatest.blablatest.base.BaseLazyFragment;


public class LoginFragment extends BaseLazyFragment<LoginPresenter> implements LoginContract.View, View.OnFocusChangeListener {


    private ProgressDialog progressDialog;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    //TODO ID
    @Override
    protected int getLayoutId() {
        return ;
    }

    @Override
    protected void initEventAndData() {
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void setupActivityComponent() {
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    public void showLoading() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("處理中...");
        progressDialog.show();
    }

    @Override
    public void completeLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", materialEditTextEmail.getText().toString());
        outState.putString("password", materialEditTextPassword.getText().toString());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            materialEditTextEmail.setText(savedInstanceState.getString("email"));
            materialEditTextPassword.setText(savedInstanceState.getString("password"));
        }
    }


    @Override
    public void onFocusChange(View view, boolean b) {
    }
}
