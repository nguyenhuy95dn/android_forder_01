package com.example.duong.android_forder_01.data.source;

/**
 * Created by Vinh on 27/02/2017.
 */
public interface DomainDataSource<T> {
    void getDatasDomain(int userId, GetDataCallback<T> getDataCallback);
    void getDatasPrivateDomainInfor(int userId, GetDataCallback<T> getDataCallback);
    void getDatasPublicDomainInfor(int userId, GetDataCallback<T> getDataCallback);
}
