package com.example.duong.android_forder_01.ui.domain.publicdomain;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.source.DomainDataSource;
import com.example.duong.android_forder_01.data.source.GetDataCallback;

import java.util.List;

/**
 * Created by Vinh on 03/03/2017.
 */
public class PublicDomainPresenter implements PublicDomainContract.Presenter {
    private PublicDomainContract.View mView;
    private DomainDataSource mDomainDataSource;

    public PublicDomainPresenter(@NonNull PublicDomainContract.View publicDomainView,
                                 DomainDataSource domainDataSource) {
        mView = publicDomainView;
        publicDomainView.setPresenter(this);
        mDomainDataSource = domainDataSource;
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void getAllPublicDomain(int idUser) {
        mDomainDataSource.getDatasPublicDomainInfor(idUser, new GetDataCallback<Domain>() {
                @Override
                public void onLoaded(List<Domain> datas) {
                    mView.showAllPublicDomain(datas);
                }

                @Override
                public void onNotAvailable() {
                    mView.showGetDataError();
                }
            });
    }
}
