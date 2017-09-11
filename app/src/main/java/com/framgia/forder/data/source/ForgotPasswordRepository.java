package com.framgia.forder.data.source;

import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import rx.Observable;

/**
 * Created by nguyenhuy95dn on 9/11/2017.
 */

public class ForgotPasswordRepository {
    private ForgotPasswordDataSource.RemoteDataSource mRemoteDataSource;

    public ForgotPasswordRepository(ForgotPasswordDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<BaseResponse> sendEmail(String userEmail) {
        return mRemoteDataSource.sendEmail(userEmail);
    }
}
