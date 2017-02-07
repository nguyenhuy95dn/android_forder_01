package com.example.duong.android_forder_01.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.duong.android_forder_01.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    @BindView(R.id.toolbar_login)
    Toolbar mToolbarLogin;
    private LoginContract.Presenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setPresenter(new LoginPresenter(this));
        mLoginPresenter.start();
    }

    @Override
    public void start() {
        ButterKnife.bind(this);
        mToolbarLogin.setTitle("");
        setSupportActionBar(mToolbarLogin);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }
}
