package com.example.duong.android_forder_01.ui.home;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.source.ProductDataSource;
import com.example.duong.android_forder_01.data.source.DomainDataSource;
import com.example.duong.android_forder_01.data.source.GetDataCallback;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mHomeView;
    private DomainDataSource mDomainDataRepository;
    private ProductDataSource mDataRepository;

    public HomePresenter(@NonNull HomeContract.View homeView, DomainDataSource domainDataSource,
                         ProductDataSource dataRepository) {
        mHomeView = homeView;
        homeView.setPresenter(this);
        mDomainDataRepository = domainDataSource;
        mDataRepository = dataRepository;
    }

    @Override
    public void start() {
        mHomeView.start();
    }

    @Override
    public void openProductResultActivity(Category categoryId) {
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

    @Override
    public void getDomain(int userId) {
        mDomainDataRepository.getDatasDomain(userId, new GetDataCallback<Domain>() {
            @Override
            public void onLoaded(List<Domain> datas) {
                mHomeView.showDomain(datas);
            }

            @Override
            public void onNotAvailable() {
                mHomeView.showGetDataError();
            }
        });
    }
}
