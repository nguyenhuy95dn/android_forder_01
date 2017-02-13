package com.example.duong.android_forder_01.ui.shopping_card;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.ShoppingCard;

public class ShoppingCardPresenter implements ShoppingCardContract.Presenter {
    private ShoppingCardContract.View mShoppingCardView;

    public ShoppingCardPresenter(@NonNull ShoppingCardContract.View shoppingCardView) {
        mShoppingCardView = shoppingCardView;
        shoppingCardView.setPresenter(this);
    }

    @Override
    public void start() {
        mShoppingCardView.start();
    }

    @Override
    public void reduceQuantity(int ShoppingCardDetailId) {
        // TODO: update shopping card in db and update ui
    }

    @Override
    public void increaseQuantity(int ShoppingCardDetailId) {
        // TODO: update shopping card in db and update ui
    }

    @Override
    public void deleteItem(int ShoppingCardDetailId) {
        // TODO: update shopping card in db and update ui
    }

    @Override
    public void order(ShoppingCard shoppingCard) {
        // TODO: send shopping card to server
    }
}
