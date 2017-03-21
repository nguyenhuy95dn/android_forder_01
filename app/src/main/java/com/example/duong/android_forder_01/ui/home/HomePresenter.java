package com.example.duong.android_forder_01.ui.home;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.DomainDataSource;
import com.example.duong.android_forder_01.data.source.GetDataCallback;
import com.example.duong.android_forder_01.data.source.ProductDataSource;
import com.example.duong.android_forder_01.data.source.ShoppingCardDataSource;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mHomeView;
    private DomainDataSource mDomainDataRepository;
    private ProductDataSource mDataRepository;
    private ShoppingCardDataSource mShoppingCardDataSource;

    public HomePresenter(@NonNull HomeContract.View homeView, DomainDataSource domainDataSource,
                         ProductDataSource dataRepository, ShoppingCardDataSource
                             shoppingCardDataSource) {
        mHomeView = homeView;
        homeView.setPresenter(this);
        mDomainDataRepository = domainDataSource;
        mDataRepository = dataRepository;
        mShoppingCardDataSource = shoppingCardDataSource;
    }

    @Override
    public void start() {
        mHomeView.start();
    }

    @Override
    public void openDomainPublic(Domain domain) {
        mHomeView.showDomainPublic(domain);
    }

    @Override
    public void openProductResultActivity(Category categoryId) {
        mHomeView.showListProduct(categoryId);
    }

    @Override
    public void getAllCategory(int domainId, User user) {
        if (user == null) return;
        mDataRepository.getDatas(domainId, user, new GetDataCallback<Category>() {
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
    public void getDomain(User user) {
        if (user == null) return;
        mDomainDataRepository.getDatasDomain(user, new GetDataCallback<Domain>() {
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

    @Override
    public void getCardItem(int domainId) {
        mHomeView.updateCard(mShoppingCardDataSource.getNumberItem(domainId));
    }
}
