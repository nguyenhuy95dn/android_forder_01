package com.example.duong.android_forder_01.ui.home;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.source.DataSource;
import com.example.duong.android_forder_01.data.model.source.GetDataCallback;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mHomeView;
    private DataSource mDataRepository;

    public HomePresenter(@NonNull HomeContract.View homeView, DataSource dataRepository) {
        mHomeView = homeView;
        homeView.setPresenter(this);
        mDataRepository = dataRepository;
    }

    @Override
    public void start() {
        mHomeView.start();
    }

    @Override
    public void openProductResultActivity(int categoryId) {
        mHomeView.showListProduct(categoryId);
    }

    @Override
    public void getAllCategory(int domainId) {
        mDataRepository.getDatas(domainId, new GetDataCallback<Category>() {
            @Override
            public void onLoaded(List<Category> datas) {
                mHomeView.showAllCategory(datas);
            }

            @Override
            public void onNotAvailable() {
                mHomeView.showGetDataError();
            }
        });
    }
}
