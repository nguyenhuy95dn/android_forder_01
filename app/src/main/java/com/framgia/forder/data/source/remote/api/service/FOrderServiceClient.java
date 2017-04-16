package com.framgia.forder.data.source.remote.api.service;

import android.app.Application;
import android.support.annotation.NonNull;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.api.middleware.RetrofitInterceptor;
import com.framgia.forder.utils.Constant;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public class FOrderServiceClient extends ServiceClient {

    private static FOrderApi mFOrderApiInstance;

    public static void initialize(@NonNull Application application) {
        SharedPrefsApi prefsApi = new SharedPrefsImpl(application);
        RetrofitInterceptor interceptor = new RetrofitInterceptor(prefsApi);
        mFOrderApiInstance =
                createService(application, Constant.END_POINT_URL, FOrderApi.class, interceptor);
    }

    public static FOrderApi getInstance() {
        if (mFOrderApiInstance == null) {
            throw new RuntimeException("Need call method FOrderServiceClient#initialize() first");
        }
        return mFOrderApiInstance;
    }
}
