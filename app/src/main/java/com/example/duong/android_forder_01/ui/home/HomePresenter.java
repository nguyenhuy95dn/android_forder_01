package com.example.duong.android_forder_01.ui.home;

import android.support.annotation.NonNull;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mHomeView;

    public HomePresenter(@NonNull HomeContract.View homeView) {
        mHomeView = homeView;
        homeView.setPresenter(this);
    }

    @Override
    public void start() {
        mHomeView.start();
    }
}
