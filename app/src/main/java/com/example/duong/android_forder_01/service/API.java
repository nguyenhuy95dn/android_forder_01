package com.example.duong.android_forder_01.service;

import com.example.duong.android_forder_01.data.model.CategoryResponse;
import com.example.duong.android_forder_01.data.model.DomainResponse;
import com.example.duong.android_forder_01.data.model.LoginResult;
import com.example.duong.android_forder_01.data.model.ProductResponse;
import com.example.duong.android_forder_01.data.model.ShopManagementResponse;

import java.util.Map;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.duong.android_forder_01.utils.Const.ConstantApi.URL_FORDER;

public abstract class API {
    private static final APIServices sAPIServices = new Retrofit.Builder()
        .baseUrl(URL_FORDER)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIServices.class);

    public static void getProduct(Map<String, String> params,
                                  Callback<ProductResponse> callback) {
        sAPIServices.getProduct(params)
            .enqueue(callback);
    }

    public static void getCategory(Map<String, String> params,
                                   Callback<CategoryResponse> callback) {
        sAPIServices.getCategory(params)
            .enqueue(callback);
    }

    public static void getDomain(Map<String, String> params,
                                 Callback<DomainResponse> callback) {
        sAPIServices.getDomain(params)
            .enqueue(callback);
    }

    public static void getLoginResult(Map<String, String> params, Callback<LoginResult> callback) {
        sAPIServices.getLoginResult(params).enqueue(callback);
    }

    public static void getShopManagement(Map<String, String> params,
                                         Callback<ShopManagementResponse> callback) {
        sAPIServices.getShopManagement(params)
            .enqueue(callback);
    }
}
