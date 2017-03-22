package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Duong on 3/22/2017.
 */
public class OrderRequest {
    @SerializedName("card")
    private ShoppingCard mShoppingCard;

    public ShoppingCard getShoppingCard() {
        return mShoppingCard;
    }

    public void setShoppingCard(ShoppingCard shoppingCard) {
        mShoppingCard = shoppingCard;
    }
}
