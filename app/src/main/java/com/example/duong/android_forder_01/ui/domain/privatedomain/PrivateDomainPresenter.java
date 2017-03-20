package com.example.duong.android_forder_01.ui.domain.privatedomain;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.DomainDataSource;
import com.example.duong.android_forder_01.data.source.GetDataCallback;

import java.util.List;

/**
 * Created by Vinh on 03/03/2017.
 */
public class PrivateDomainPresenter implements PrivateDomainContract.Presenter {
    private PrivateDomainContract.View mView;
    private DomainDataSource mDomainDataSource;

    public PrivateDomainPresenter(@NonNull PrivateDomainContract.View privateDomainView,
                                  DomainDataSource domainDataSource) {
        mView = privateDomainView;
        privateDomainView.setPresenter(this);
        mDomainDataSource = domainDataSource;
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void getAllPrivateDomain(User user) {
        if (user == null) return;
        mDomainDataSource
            .getDatasPrivateDomainInfor(user, new GetDataCallback<Domain>() {
                @Override
                public void onLoaded(List<Domain> datas) {
                    mView.showAllPrivateDomain(datas);
                }

                @Override
                public void onNotAvailable() {
                    mView.showGetDataError();
                }
            });
    }

    @Override
    public void getDomainDetail(Domain domain) {
        if (domain == null) return;
        mView.showDomainDetail(domain);
    }
}
