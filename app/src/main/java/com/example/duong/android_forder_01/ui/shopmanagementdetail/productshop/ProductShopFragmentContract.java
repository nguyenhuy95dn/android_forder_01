package com.example.duong.android_forder_01.ui.shopmanagementdetail.productshop;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

/**
 * Created by tri on 07/03/2017.
 */
public interface ProductShopFragmentContract {
    interface View extends BaseView<Presenter> {
        void showDetailShop(Product product);
        void showGetDataError();
        void showUiUpdateProduct(Product product);
        void showUiAddProduct(Product product);
    }

    interface Presenter extends BasePresenter {
        void itemClick(Product product);
        void deleteProduct(Product product);
        void updateProduct(Product product);
        void addProduct(Product product);
    }
}
