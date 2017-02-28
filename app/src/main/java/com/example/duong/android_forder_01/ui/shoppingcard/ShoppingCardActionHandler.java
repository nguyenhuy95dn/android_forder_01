package com.example.duong.android_forder_01.ui.shoppingcard;

import com.example.duong.android_forder_01.data.model.ShoppingCard;

public class ShoppingCardActionHandler {
    private ShoppingCardContract.Presenter mListener;

    public ShoppingCardActionHandler(ShoppingCardContract.Presenter listener) {
        mListener = listener;
    }

    public void reduceQuantity(int shoppingCardDetailId) {
        mListener.reduceQuantity(shoppingCardDetailId);
    }

    public void increaseQuantity(int shoppingCardDetailId) {
        mListener.increaseQuantity(shoppingCardDetailId);
    }

    public void deleteItem(int shoppingCardDetailId) {
        mListener.deleteItem(shoppingCardDetailId);
    }

    public void order(ShoppingCard shoppingCard) {
        mListener.order(shoppingCard);
    }

    public void orderAll() {
    }
}
