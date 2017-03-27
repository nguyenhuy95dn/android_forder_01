package com.example.duong.android_forder_01.service;

import com.example.duong.android_forder_01.data.model.CategoryResponse;
import com.example.duong.android_forder_01.data.model.DomainResponse;
import com.example.duong.android_forder_01.data.model.LoginResult;
import com.example.duong.android_forder_01.data.model.ProductResponse;
import com.example.duong.android_forder_01.data.model.ShopManagementResponse;
import com.example.duong.android_forder_01.data.model.ShopResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PATH_CATEGORY;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PATH_DOMAIN;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PATH_LOGIN;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PATH_PRODUCT;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PATH_SHOP;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PATH_SHOP_MANAGEMENT;

public interface APIServices {
    @GET(PATH_LOGIN)
    Call<LoginResult> getLoginResult(@QueryMap Map<String, String> params);
    @GET(PATH_PRODUCT)
    Call<ProductResponse> getProduct(@QueryMap Map<String, String> params);
    @GET(PATH_CATEGORY)
    Call<CategoryResponse> getCategory(@QueryMap Map<String, String> params);
    @GET(PATH_DOMAIN)
    Call<DomainResponse> getDomain(@QueryMap Map<String, String> params);
    @GET(PATH_SHOP_MANAGEMENT)
    Call<ShopManagementResponse> getShopManagement(@QueryMap Map<String, String> params);
    @GET(PATH_SHOP)
    Call<ShopResponse> getShop(@QueryMap Map<String, String> params);
}
