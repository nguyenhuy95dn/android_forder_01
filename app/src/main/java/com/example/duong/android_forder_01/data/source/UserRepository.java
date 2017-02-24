package com.example.duong.android_forder_01.data.source;

import android.content.Context;

import com.example.duong.android_forder_01.data.model.LoginResult;
import com.example.duong.android_forder_01.data.source.remote.UserRemoteDataSource;

public class UserRepository implements UserDataSource {
    private static UserRepository sUserRepository;
    private UserDataSource mUserRemoteDataSource;

    private UserRepository(UserRemoteDataSource userRemoteDataSource) {
        mUserRemoteDataSource = userRemoteDataSource;
    }

    public static UserRepository getInstance(Context context) {
        if (sUserRepository == null) {
            sUserRepository = new UserRepository(UserRemoteDataSource.getInstance());
        }
        return sUserRepository;
    }

    @Override
    public void getLoginResult(String userName, String password,
                               final LoginCallback callback) {
        mUserRemoteDataSource.getLoginResult(userName, password, new LoginCallback() {
            @Override
            public void onLogin(LoginResult loginResult) {
                callback.onLogin(loginResult);
            }

            @Override
            public void onLoginError() {
                callback.onLoginError();
            }
        });
    }
}
