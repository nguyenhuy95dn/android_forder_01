package com.example.duong.android_forder_01.service;

import com.example.duong.android_forder_01.data.model.ProductResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PATH_PRODUCT;

public interface APIServices {
    @GET(PATH_PRODUCT)
    Call<ProductResponse> getProduct(@QueryMap Map<String, String> params);
}
