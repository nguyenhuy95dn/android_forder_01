package com.example.duong.android_forder_01.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private Toolbar mToolbarLogin;
    private LoginContract.Presenter mLoginPresenter;
    private ActivityLoginBinding mActivityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setPresenter(new LoginPresenter(this));
        mLoginPresenter.start();
    }

    @Override
    public void start() {
        mToolbarLogin = mActivityLoginBinding.toolbarLogin;
        mToolbarLogin.setTitle("");
        setSupportActionBar(mToolbarLogin);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }
}
