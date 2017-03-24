package com.example.duong.android_forder_01.data.source.remote;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.data.model.ShopManagementResponse;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.GetDataCallback;
import com.example.duong.android_forder_01.data.source.ShopDataSource;
import com.example.duong.android_forder_01.service.API;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_USER_EMAIL;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_USER_ID;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_USER_TOKEN;

/**
 * Created by tri on 24/02/2017.
 */
public class ShopRemoteDataSource implements ShopDataSource {
    private static ShopRemoteDataSource sShopRemoteDataSource;
    private Context mContext;

    private ShopRemoteDataSource() {
    }

    public static ShopRemoteDataSource getInstance() {
        if (sShopRemoteDataSource == null) {
            sShopRemoteDataSource = new ShopRemoteDataSource();
        }
        return sShopRemoteDataSource;
    }

    @Override
    public void getDataShop(int domainId, User user, final GetDataCallback<Shop> getDataCallback) {
        if (getDataCallback == null) return;
    }

    @Override
    public void getDataShopManagement(User user,
                                      final GetDataCallback<ShopManagement> getDataCallback) {
        if (getDataCallback == null || user == null) return;
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_USER_ID, String.valueOf(user.getId()));
        params.put(PARAM_USER_EMAIL, user.getUserName());
        params.put(PARAM_USER_TOKEN, user.getAuthenticationToken());
        API.getShopManagement(params, new Callback<ShopManagementResponse>() {
                @Override
                public void onResponse(Call<ShopManagementResponse> call,
                                       Response<ShopManagementResponse> response) {
                    if (response == null || response.body().getShopManagementList() == null) {
                        getDataCallback.onNotAvailable();
                        return;
                    }
                    getDataCallback.onLoaded(response.body().getShopManagementList());
                }

                @Override
                public void onFailure(Call<ShopManagementResponse> call, Throwable t) {
                    getDataCallback.onNotAvailable();
                }
            }
        );
    }
}
