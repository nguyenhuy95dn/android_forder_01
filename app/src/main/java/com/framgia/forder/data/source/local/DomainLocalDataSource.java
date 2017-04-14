package com.framgia.forder.data.source.local;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.DomainDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsKey;

/**
 * Created by Age on 4/13/2017.
 */

public class DomainLocalDataSource implements DomainDataSource.LocalDataSource {

    private SharedPrefsApi mSharedPrefsApi;

    public DomainLocalDataSource(@NonNull SharedPrefsApi prefsApi) {
        mSharedPrefsApi = prefsApi;
    }

    @Override
    public User getUser() {
        return mSharedPrefsApi.get(SharedPrefsKey.KEY_USER, User.class);
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
