package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.response.UserResponse;
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.Type;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by levutantuan on 4/13/17.
 */

public class UserRemoteDataSource extends BaseRemoteDataSource
        implements UserDataSource.RemoteDataSource {

    public UserRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<User> login(final String userName, String passWord) {
        return mFOrderApi.login(userName, passWord)
                .flatMap(new Func1<UserResponse, Observable<User>>() {

                    @Override
                    public Observable<User> call(UserResponse userReponse) {
                        if (userReponse == null) {
                            return Observable.just(null);
                        }
                        if (userReponse.getStatus() != 200) {
                            return Observable.error(new BaseException(Type.NETWORK,
                                    new Throwable(userReponse.getMessage())));
                        }
                        return Observable.just(userReponse.getUser());
                    }
                });
    }
}
