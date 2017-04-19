package com.framgia.forder.data.source;

import com.framgia.forder.data.model.User;
import rx.Observable;

/**
 * Created by levutantuan on 4/13/17.
 */

public class UserDataSource {

    public interface LocalDataSource {
        void saveUser(User user);

        User getUser();

        void clearData();
    }

    public interface RemoteDataSource {
        Observable<User> login(String userName, String passWord);

        Observable<User> updateProfile(String newPassword, String chatWorkId, String description,
                String currentPassword);
    }
}
