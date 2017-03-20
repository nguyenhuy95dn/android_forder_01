package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.CategoryResponse;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.GetDataCallback;
import com.example.duong.android_forder_01.data.source.ProductDataSource;
import com.example.duong.android_forder_01.service.API;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_DOMAIN_ID;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_USER_EMAIL;
import static com.example.duong.android_forder_01.utils.Const.ConstantApi.PARAM_USER_TOKEN;

/**
 * Created by tri on 27/02/2017.
 */
public class CategoryRemoteDataSource implements ProductDataSource<Category> {
    private static CategoryRemoteDataSource sCategoryRemoteDataSource;

    private CategoryRemoteDataSource() {
    }

    public static CategoryRemoteDataSource getInstance() {
        if (sCategoryRemoteDataSource == null) {
            sCategoryRemoteDataSource = new CategoryRemoteDataSource();
        }
        return sCategoryRemoteDataSource;
    }

    @Override
    public void getDatas(int idDomain, User user, final GetDataCallback<Category> getDataCallback) {
        if (getDataCallback == null && user == null) return;
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_DOMAIN_ID, String.valueOf(idDomain));
        params.put(PARAM_USER_EMAIL, user.getUserName());
        params.put(PARAM_USER_TOKEN, user.getAuthenticationToken());
        API.getCategory(params, new Callback<CategoryResponse>() {
                @Override
                public void onResponse(Call<CategoryResponse> call,
                                       Response<CategoryResponse> response) {
                    if (response == null && response.body().getCategoryList() == null) {
                        getDataCallback.onNotAvailable();
                        return;
                    }
                    getDataCallback.onLoaded(response.body().getCategoryList());
                }

                @Override
                public void onFailure(Call<CategoryResponse> call, Throwable t) {
                    getDataCallback.onNotAvailable();
                }
            }
        );
    }

    @Override
    public void getProductByCategoryId(int domainId, int categoryId, User user,
                                       GetDataCallback<Category> getDataCallback) {
        // not required
    }
}
