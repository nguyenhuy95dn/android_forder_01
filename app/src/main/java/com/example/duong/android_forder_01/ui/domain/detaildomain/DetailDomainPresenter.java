package com.example.duong.android_forder_01.ui.domain.detaildomain;

import android.support.annotation.NonNull;

/**
 * Created by Vinh on 07/03/2017.
 */
public class DetailDomainPresenter implements DetailDomainContract.Presenter {
    private DetailDomainContract.View mView;

    public DetailDomainPresenter(@NonNull DetailDomainContract.View view) {
        mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }
}
