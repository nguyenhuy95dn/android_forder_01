package com.example.duong.android_forder_01.ui.home.product;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

public interface ProductContract {
    interface View extends BaseView<Presenter> {
        void initRecyclerView();
        void showProductDetail(Product product);
        void showAllProduct(List<Product> list);
        void showGetDataError();
    }

    interface Presenter extends BasePresenter {
        void openShopDetail(Shop shop);
        void openProductDetail(Product product);
        void addShoppingCard(Product product);
        void getAllProduct(int idDomain);
    }
}
