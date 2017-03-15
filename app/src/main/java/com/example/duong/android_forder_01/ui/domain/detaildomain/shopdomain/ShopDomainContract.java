package com.example.duong.android_forder_01.ui.domain.detaildomain.shopdomain;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

/**
 * Created by Vinh on 07/03/2017.
 */
public interface ShopDomainContract {
    interface View extends BaseView<Presenter> {
        void showAllShopDomain(List<Shop> list);
        void showGetDataError();
    }

    interface Presenter extends BasePresenter {
        void getAllShopDomain(int idDomain);
    }
}
