package com.example.duong.android_forder_01.ui.domain;

import android.support.annotation.NonNull;

/**
 * Created by Vinh on 03/03/2017.
 */
public class DomainPresenter implements DomainContract.Presenter {
    private DomainContract.View mView;

    public DomainPresenter(@NonNull DomainContract.View view) {
        mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }
}
