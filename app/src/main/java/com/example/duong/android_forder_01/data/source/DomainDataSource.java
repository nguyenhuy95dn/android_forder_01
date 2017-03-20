package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.User;

/**
 * Created by Vinh on 27/02/2017.
 */
public interface DomainDataSource {
    void getDatasDomain(User user, GetDataCallback<Domain> getDataCallback);
    void getDatasPrivateDomainInfor(User user, GetDataCallback<Domain> getDataCallback);
    void getDatasPublicDomainInfor(User user, GetDataCallback<Domain> getDataCallback);
}
