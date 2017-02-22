package com.example.duong.android_forder_01.data.model.source;

import com.example.duong.android_forder_01.data.model.ShoppingCard;

public interface ShoppingCardDataSource {
    void saveShoppingCard(ShoppingCard shoppingCard);
    void getShoppingCard(int idDomain, GetDataCallback<ShoppingCard> getCallback);
    boolean isExistsDomainAndShop(ShoppingCard shoppingCard);
    int totalPriceShoppingCard(int idDomain);
}
