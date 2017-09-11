package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.source.ForgotPasswordDataSource;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import rx.Observable;

/**
 * Created by nguyenhuy95dn on 9/11/2017.
 */

public class ForgotPasswordRemoteDataSource extends BaseRemoteDataSource
        implements ForgotPasswordDataSource.RemoteDataSource {

    public ForgotPasswordRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<BaseResponse> sendEmail(String userEmail) {
        return mFOrderApi.sendEmail(userEmail);
    }
}
