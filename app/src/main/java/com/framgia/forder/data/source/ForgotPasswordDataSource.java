package com.framgia.forder.data.source;

import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import rx.Observable;

/**
 * Created by nguyenhuy95dn on 9/11/2017.
 */

public class ForgotPasswordDataSource {
    public interface RemoteDataSource {
        Observable<BaseResponse> sendEmail(String userEmail);
    }
}
