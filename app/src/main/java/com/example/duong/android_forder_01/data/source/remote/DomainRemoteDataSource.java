package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Avatar;
import com.example.duong.android_forder_01.data.model.CollectionAvatar;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.Thumbnail;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.DomainDataSource;
import com.example.duong.android_forder_01.data.source.GetDataCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 27/02/2017.
 */
public class DomainRemoteDataSource implements DomainDataSource {
    public static DomainRemoteDataSource sDomainRemoteDataSource;

    private DomainRemoteDataSource() {
    }

    public static DomainRemoteDataSource getInstance() {
        if (sDomainRemoteDataSource == null) {
            sDomainRemoteDataSource = new DomainRemoteDataSource();
        }
        return sDomainRemoteDataSource;
    }

    @Override
    public void getDatasDomain(int userId, final GetDataCallback getDataCallback) {
        if (getDataCallback == null) return;
        //TODO: Load domain
    }

    @Override
    public void getDatasPrivateDomainInfor(int userId, final GetDataCallback getDataCallback) {
        if (getDataCallback == null) return;
        //TODO: Load private domain
    }

    @Override
    public void getDatasPublicDomainInfor(int userId, final GetDataCallback getDataCallback) {
        if (getDataCallback == null) return;
        //TODO: Load public domain
    }
}
