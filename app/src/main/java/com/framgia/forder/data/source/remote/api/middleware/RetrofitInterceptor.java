package com.framgia.forder.data.source.remote.api.middleware;

import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsKey;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sun on 4/16/2017.
 */

public class RetrofitInterceptor implements Interceptor {
    
    private SharedPrefsApi mPrefsApi;

    public RetrofitInterceptor(SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = initializeHeader(chain);
        Request request = builder.build();
        Response response = chain.proceed(request);
        return response;
    }

    private Request.Builder initializeHeader(Chain chain) {
        String accessToken = mPrefsApi.get(SharedPrefsKey.KEY_USER_TOKEN, String.class);
        Request originRequest = chain.request();
        return originRequest.newBuilder()
                .header("Accept", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Cache-Control", "no-store")
                .header("Authorization", "Bearer " + accessToken)
                .method(originRequest.method(), originRequest.body());
    }
}
