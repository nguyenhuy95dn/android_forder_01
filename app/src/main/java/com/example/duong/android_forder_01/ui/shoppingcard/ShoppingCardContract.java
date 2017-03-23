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
        void showConfirmOrder(String message);
        void updateCard(int numberItem);
    }

    interface Presenter extends BasePresenter {
        void loadShoppingCard(int domainId);
        void reduceQuantity(int productId, int domainId);
        void increaseQuantity(int productId, int domainId);
        void deleteItem(int productId, int domainId);
        void deleteShoppingCardOfDomain(int domainId);
        void order(ShoppingCard shoppingCard, int domainId);
        void orderAll(List<ShoppingCard> list, int domainId);
        void acceptOrder(List<ShoppingCard> list);
        String getAlertMessage(List<String> list, double total);
    }
}
