package com.example.duong.android_forder_01.ui.productdetail;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

public interface ProductDetailContract {
    interface View extends BaseView<Presenter> {
        void initToolbar();
    }

    interface Presenter extends BasePresenter {
        void addShoppingCard(Product product);
        void order(Product product);
    }
}
