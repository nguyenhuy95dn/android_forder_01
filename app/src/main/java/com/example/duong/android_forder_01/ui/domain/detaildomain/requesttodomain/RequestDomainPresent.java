package com.example.duong.android_forder_01.ui.domain.detaildomain.requesttodomain;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.GetDataCallback;
import com.example.duong.android_forder_01.data.source.ShopDataSource;

import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.ID_ROOT_DOMAIN;

/**
 * Created by Vinh on 07/03/2017.
 */
public class RequestDomainPresent implements RequestDomainContract.Presenter {
    private RequestDomainContract.View mView;
    private ShopDataSource mDataRepository;

    public RequestDomainPresent(@NonNull RequestDomainContract.View view,
                                ShopDataSource dataRepository) {
        mView = view;
        view.setPresenter(this);
        mDataRepository = dataRepository;
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void getAllShopRequestDomain(int domainId, User user) {
        if (user == null) return;
    }
}
