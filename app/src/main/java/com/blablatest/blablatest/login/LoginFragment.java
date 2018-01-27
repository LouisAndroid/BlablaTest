package com.blablatest.blablatest.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blablatest.blablatest.R;
import com.blablatest.blablatest.base.BaseLazyFragment;
import com.blablatest.blablatest.map.MapFragment;
import com.dd.processbutton.iml.ActionProcessButton;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;


public class LoginFragment extends BaseLazyFragment<LoginPresenter> implements LoginContract.View, View.OnFocusChangeListener {


    private ActionProcessButton btnSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewCurrentState;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.checkLogin();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getActivity())));
    }

    @Override
    protected void setupActivityComponent() {
        textViewCurrentState = (TextView) mView.findViewById(R.id.textView_current_state);
        btnSignIn = (ActionProcessButton) mView.findViewById(R.id.btnSignIn);
        editTextEmail = (EditText) mView.findViewById(R.id.editEmail);
        editTextPassword = (EditText) mView.findViewById(R.id.editPassword);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login();
            }
        });
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
        outState.putString("email", editTextEmail.getText().toString());
        outState.putString("password", editTextPassword.getText().toString());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            editTextEmail.setText(savedInstanceState.getString("email"));
            editTextPassword.setText(savedInstanceState.getString("password"));
        }
    }


    @Override
    public void onFocusChange(View view, boolean b) {
    }

    @Override
    public String getEmail() {
        return editTextEmail.getText().toString();
    }

    @Override
    public void setCurrentState(String text) {
        textViewCurrentState.setText(text);
    }

    @Override
    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    @Override
    public void setLoginBtMode(ActionProcessButton.Mode mode) {
        btnSignIn.setMode(mode);
    }


    @Override
    public void loginSuccess() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_box, MapFragment.newInstance())
                .commit();
    }
}
