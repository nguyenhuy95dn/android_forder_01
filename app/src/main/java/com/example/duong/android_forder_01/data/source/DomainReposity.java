package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.remote.DomainRemoteDataSource;

import java.util.List;

/**
 * Created by Vinh on 27/02/2017.
 */
public class DomainReposity implements DomainDataSource {
    private static DomainReposity sDomainReposity;
    private DomainDataSource mDomainDataSource;

    private DomainReposity(DomainRemoteDataSource domainRemoteDataSource) {
        mDomainDataSource = domainRemoteDataSource;
    }

    public static DomainReposity getInstance() {
        if (sDomainReposity == null) {
            sDomainReposity = new DomainReposity(DomainRemoteDataSource.getInstance());
        }
        return sDomainReposity;
    }

    @Override
    public void getDatasDomain(User user, final GetDataCallback getDataCallback) {
        if (getDataCallback == null && user == null) return;
        mDomainDataSource.getDatasDomain(user, new GetDataCallback<Domain>() {
            @Override
            public void onLoaded(List datas) {
                getDataCallback.onLoaded(datas);
            }

            @Override
            public void onNotAvailable() {
                getDataCallback.onNotAvailable();
            }
        });
    }

    @Override
    public void getDatasPrivateDomainInfor(User user, final GetDataCallback getDataCallback) {
        if (getDataCallback == null && user == null) return;
        mDomainDataSource.getDatasPrivateDomainInfor(user, new GetDataCallback<Domain>() {
            @Override
            public void onLoaded(List datas) {
                getDataCallback.onLoaded(datas);
            }

            @Override
            public void onNotAvailable() {
                getDataCallback.onNotAvailable();
            }
        });
    }

    @Override
    public void getDatasPublicDomainInfor(User user, final GetDataCallback getDataCallback) {
        if (getDataCallback == null && user == null) return;
        mDomainDataSource.getDatasPublicDomainInfor(user, new GetDataCallback<Domain>() {
            @Override
            public void onLoaded(List datas) {
                getDataCallback.onLoaded(datas);
            }

            @Override
            public void onNotAvailable() {
                getDataCallback.onNotAvailable();
            }
        });
    }
}
