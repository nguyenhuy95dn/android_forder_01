package com.example.duong.android_forder_01.data.model.source.local;

import android.content.Context;

import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.model.source.GetDataCallback;
import com.example.duong.android_forder_01.data.model.source.ShoppingCardDataSource;

/**
 * Created by tri on 22/02/2017.
 */
public class ShoppingCardLocalDataSource extends DataHelper implements ShoppingCardDataSource {
    private static ShoppingCardLocalDataSource sLocalDataSource;

    private ShoppingCardLocalDataSource(Context context) {
        super(context);
    }

    public static ShoppingCardLocalDataSource getInstance(Context context) {
        if (sLocalDataSource == null) {
            sLocalDataSource = new ShoppingCardLocalDataSource(context);
        }
        return sLocalDataSource;
    }

    @Override
    public void saveShoppingCard(ShoppingCard shoppingCard) {
        // TODO save shopping card
    }

    @Override
    public void getShoppingCard(int idDomain, GetDataCallback<ShoppingCard> getDataCallback) {
        // TODO get list shopping card
    }

    @Override
    public boolean isExistsDomainAndShop(ShoppingCard shoppingCard) {
        return false;
        // TODO check id domain and shop
    }

    @Override
    public int totalPriceShoppingCard(int idDomain) {
        return 0;
        // TODO total price shopping card
    }
}
