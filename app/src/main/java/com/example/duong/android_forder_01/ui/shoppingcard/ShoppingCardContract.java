package com.example.duong.android_forder_01.ui.shoppingcard;

import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

public interface ShoppingCardContract {
    interface View extends BaseView<Presenter> {
        void initRecyclerView();
        void initToolbar();
    }

    interface Presenter extends BasePresenter {
        void reduceQuantity(int ShoppingCardDetailId);
        void increaseQuantity(int ShoppingCardDetailId);
        void deleteItem(int ShoppingCardDetailId);
        void order(ShoppingCard shoppingCard);
    }
}
