package com.example.duong.android_forder_01.ui.domain.detaildomain.requesttodomain;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

/**
 * Created by Vinh on 07/03/2017.
 */
public interface RequestDomainContract {
    interface View extends BaseView<Presenter> {
        void showAllShopRequestDomain(List<Shop> list);
        void showGetDataError();
    }

    interface Presenter extends BasePresenter {
        void getAllShopRequestDomain(int idDomain);
    }
}
