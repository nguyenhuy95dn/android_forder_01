package com.example.duong.android_forder_01.ui.login;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.LoginResult;
import com.example.duong.android_forder_01.data.source.UserDataSource;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mLoginView;
    private UserDataSource mUserDataSource;

    public LoginPresenter(@NonNull LoginContract.View loginView, UserDataSource userDataSource) {
        mLoginView = loginView;
        loginView.setPresenter(this);
        mUserDataSource = userDataSource;
    }

    @Override
    public void start() {
        mLoginView.start();
    }

    @Override
    public void login(String userName, String password) {
        mLoginView.showProgress(true);
        mUserDataSource.getLoginResult(userName, password, new UserDataSource.LoginCallback() {
            @Override
            public void onLogin(LoginResult loginResult) {
                if (loginResult.getUser() == null) {
                    mLoginView.showLoginError(loginResult.getMessage());
                    return;
                }
                mLoginView.showProgress(false);
                mLoginView.loginComplete(loginResult.getUser());
            }

            @Override
            public void onLoginError() {
                mLoginView.showProgress(false);
                mLoginView.showRequestError();
            }
        });
    }
}
