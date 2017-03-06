package com.example.duong.android_forder_01.ui.shoppingcard;

import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

public interface ShoppingCardContract {
    interface View extends BaseView<Presenter> {
        void initRecyclerView();
        void initToolbar();
        void loadShoppingCardCompleted(List<ShoppingCard> list, double totalPrice);
        void loadShoppingCardError();
    }

    interface Presenter extends BasePresenter {
        void loadShoppingCard(int domainId);
        void reduceQuantity(int productId, int domainId);
        void increaseQuantity(int productId, int domainId);
        void deleteItem(int productId, int domainId);
        void order(ShoppingCard shoppingCard);
        void orderAll(List<ShoppingCard> list);
    }
}
