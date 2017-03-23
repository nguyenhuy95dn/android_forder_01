package com.example.duong.android_forder_01.ui.shoppingcard;

import android.content.Context;

import com.example.duong.android_forder_01.data.model.ShoppingCard;

import java.util.List;

import static com.example.duong.android_forder_01.utils.SharedPreferencesUtils.getCurrentDomain;

public class ShoppingCardActionHandler {
    private ShoppingCardContract.Presenter mListener;
    private Context mContext;

    public ShoppingCardActionHandler(ShoppingCardContract.Presenter listener, Context context) {
        mListener = listener;
        mContext = context;
    }

    public void reduceQuantity(int productId) {
        mListener.reduceQuantity(productId, getCurrentDomain(mContext).getId());
    }

    public void increaseQuantity(int productId) {
        mListener.increaseQuantity(productId, getCurrentDomain(mContext).getId());
    }

    public void deleteItem(int productId) {
        mListener.deleteItem(productId, getCurrentDomain(mContext).getId());
    }

    public void order(ShoppingCard shoppingCard) {
        mListener.order(shoppingCard, getCurrentDomain(mContext).getId());
    }

    public void orderAll(List<ShoppingCard> list) {
        mListener.orderAll(list, getCurrentDomain(mContext).getId());
    }
}
