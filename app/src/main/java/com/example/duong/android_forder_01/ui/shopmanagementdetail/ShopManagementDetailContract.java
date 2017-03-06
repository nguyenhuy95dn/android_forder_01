package com.example.duong.android_forder_01.ui.shopmanagementdetail;

import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;
import com.example.duong.android_forder_01.ui.shopdetail.ShopDetailContract;

/**
 * Created by tri on 06/03/2017.
 */
public interface ShopManagementDetailContract {
    interface View extends BaseView<ShopManagementDetailContract.Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
