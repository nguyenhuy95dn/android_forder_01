package com.example.duong.android_forder_01.ui.productdetail;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

public interface ProductDetailContract {
    interface View extends BaseView<Presenter> {
        void initToolbar();
        void initRecyclerRelateProduct();
    }

    interface Presenter extends BasePresenter {
        void addShoppingCard(Product product);
        void order(Product product);
        void itemRelateProductClick(Product product);
        void viewAllComment(int productId);
        void viewAllProduct(int shopId);
    }
}
