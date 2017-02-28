package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.LoginResult;
import com.example.duong.android_forder_01.data.source.UserDataSource;
import com.example.duong.android_forder_01.service.API;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_PASSWORD;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_USER_NAME;

public class UserRemoteDataSource implements UserDataSource {
    private static UserRemoteDataSource sUserRemoteDataSource;

    private UserRemoteDataSource() {
    }

    public static UserRemoteDataSource getInstance() {
        if (sUserRemoteDataSource == null) {
            sUserRemoteDataSource = new UserRemoteDataSource();
        }
        return sUserRemoteDataSource;
    }

    @Override
    public void getLoginResult(String userName, String password,
                               final LoginCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_USER_NAME, userName);
        params.put(PARAM_PASSWORD, password);
        API.getLoginResult(params, new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response == null || response.body() == null) {
                    callback.onLoginError();
                    return;
                }
                callback.onLogin(response.body());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                callback.onLoginError();
            }
        });
    }
}
