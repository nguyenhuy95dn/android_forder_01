package com.example.duong.android_forder_01.ui.login;

import android.support.annotation.NonNull;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mLoginView;

    public LoginPresenter(@NonNull LoginContract.View loginView) {
        mLoginView = loginView;
        loginView.setPresenter(this);
    }

    @Override
    public void start() {
        mLoginView.start();
    }
}
