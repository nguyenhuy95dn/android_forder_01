package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.LoginResult;

/**
 * Created by Duong on 2/22/2017.
 */
public interface UserDataSource {
    void getLoginResult(String userName, String password, LoginCallback callback);
    interface LoginCallback {
        void onLogin(LoginResult loginResult);
        void onLoginError();
    }
}
