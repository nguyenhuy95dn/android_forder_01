package com.framgia.forder.data.source;

import com.framgia.forder.data.model.User;
import rx.Observable;

/**
 * Created by levutantuan on 4/13/17.
 */

public class UserRepository {
    private UserDataSource.RemoteDataSource mUserRemoteDataSource;
    private UserDataSource.LocalDataSource mUserLocalDataSource;

    public UserRepository(UserDataSource.RemoteDataSource userRemoteDataSource,
            UserDataSource.LocalDataSource userLocalDataSource) {
        mUserRemoteDataSource = userRemoteDataSource;
        mUserLocalDataSource = userLocalDataSource;
    }

    public Observable<User> login(String userName, String passWord) {
        return mUserRemoteDataSource.login(userName, passWord);
    }

    public UserDataSource.RemoteDataSource getUserRemoteDataSource() {
        return mUserRemoteDataSource;
    }

    public void setUserRemoteDataSource(UserDataSource.RemoteDataSource userRemoteDataSource) {
        mUserRemoteDataSource = userRemoteDataSource;
    }

    public UserDataSource.LocalDataSource getUserLocalDataSource() {
        return mUserLocalDataSource;
    }

    public void setUserLocalDataSource(UserDataSource.LocalDataSource userLocalDataSource) {
        mUserLocalDataSource = userLocalDataSource;
    }

    public void saveUser(User user) {
        mUserLocalDataSource.saveUser(user);
    }

    public User getUser() {
        return mUserLocalDataSource.getUser();
    }
}
