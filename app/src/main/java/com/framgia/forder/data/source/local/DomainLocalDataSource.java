package com.framgia.forder.data.source.local;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.DomainDataSource;
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsKey;

/**
 * Created by Age on 4/13/2017.
 */

public class DomainLocalDataSource implements DomainDataSource.LocalDataSource {

    private SharedPrefsApi mSharedPrefsApi;
    private UserDataSource.LocalDataSource mUserLocalDataSource;

    public DomainLocalDataSource(@NonNull SharedPrefsApi prefsApi,
            UserDataSource.LocalDataSource userLocalDataSource) {
        mSharedPrefsApi = prefsApi;
        mUserLocalDataSource = userLocalDataSource;
    }

    @Override
    public User getUser() {
        return mUserLocalDataSource.getUser();
    }

    @Override
    public void saveDomainId(int id) {
        mSharedPrefsApi.put(SharedPrefsKey.KEY_DOMAIN_ID, id);
    }

    @Override
    public int getDomainId() {
        return mSharedPrefsApi.get(SharedPrefsKey.KEY_DOMAIN_ID, Integer.class);
    }
}
