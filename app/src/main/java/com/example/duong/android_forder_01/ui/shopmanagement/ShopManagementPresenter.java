package com.example.duong.android_forder_01.ui.shopmanagement;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.ShopDomain;
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.GetDataCallback;
import com.example.duong.android_forder_01.data.source.ShopDataSource;

import java.util.List;

/**
 * Created by tri on 02/03/2017.
 */
public class ShopManagementPresenter implements ShopManagementContract.Presenter {
    private ShopManagementContract.View mView;
    private ShopDataSource mDataRepository;

    public ShopManagementPresenter(ShopManagementContract.View view,
                                   ShopDataSource shopDataSource) {
        mView = view;
        mView.setPresenter(this);
        mDataRepository = shopDataSource;
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void getShopByUser(User user) {
        if (user == null) return;
        mDataRepository.getDataShopManagement(user, new GetDataCallback<ShopManagement>() {
            @Override
            public void onLoaded(List<ShopManagement> datas) {
                mView.showAllShop(datas);
            }

            @Override
            public void onNotAvailable() {
                mView.showGetDataError();
            }
        });
    }

    @Override
    public void sendRequest(ShopDomain shopDomain, Shop shop) {
        // TODO send request join shop into domain
    }

    @Override
    public void cancelRequest(ShopDomain shopDomain, Shop shop) {
        // TODO cancel request out shop into domain
    }

    @Override
    public void rejectRequest(ShopDomain shopDomain, Shop shop) {
        // TODO reject request out shop into domain
    }

    @Override
    public void openDetailShop(ShopManagement shopManagement) {
        mView.showDetailShop(shopManagement);
    }
}
