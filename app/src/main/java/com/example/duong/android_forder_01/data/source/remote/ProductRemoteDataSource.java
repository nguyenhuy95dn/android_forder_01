package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.ProductResponse;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.GetDataCallback;
import com.example.duong.android_forder_01.data.source.ProductDataSource;
import com.example.duong.android_forder_01.service.API;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_CATEGORY_ID;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_DOMAIN_ID;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_USER_EMAIL;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_USER_TOKEN;

/**
 * Created by tri on 24/02/2017.
 */
public class ProductRemoteDataSource implements ProductDataSource<Product> {
    private static ProductRemoteDataSource sProductRemoteDataSource;

    private ProductRemoteDataSource() {
    }

    public static ProductRemoteDataSource getInstance() {
        if (sProductRemoteDataSource == null) {
            sProductRemoteDataSource = new ProductRemoteDataSource();
        }
        return sProductRemoteDataSource;
    }

    @Override
    public void getDatas(final int idDomain, final User user,
                         final GetDataCallback<Product> getDataCallback) {
        if (getDataCallback == null && user == null) return;
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_DOMAIN_ID, String.valueOf(idDomain));
        params.put(PARAM_USER_EMAIL, user.getUserName());
        params.put(PARAM_USER_TOKEN, user.getAuthenticationToken());
        API.getProduct(params, new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if (response == null && response.body().getProductsList() == null) {
                        getDataCallback.onNotAvailable();
                        return;
                    }
                    getDataCallback.onLoaded(response.body().getProductsList());
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    getDataCallback.onNotAvailable();
                }
            }
        );
    }

    @Override
    public void getProductByCategoryId(int domainId, int categoryId, User user,
                                       final GetDataCallback<Product> getDataCallback) {
        if (getDataCallback == null && user == null) return;
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_CATEGORY_ID, String.valueOf(domainId));
        params.put(PARAM_DOMAIN_ID, String.valueOf(categoryId));
        params.put(PARAM_USER_EMAIL, user.getUserName());
        params.put(PARAM_USER_TOKEN, user.getAuthenticationToken());
        API.getProduct(params, new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if (response == null && response.body().getProductsList() == null) {
                        getDataCallback.onNotAvailable();
                        return;
                    }
                    getDataCallback.onLoaded(response.body().getProductsList());
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    getDataCallback.onNotAvailable();
                }
            }
        );
    }
}
