package com.example.duong.android_forder_01.data.model.source;

import android.content.Context;

import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.model.source.local.ShoppingCardLocalDataSource;

import java.util.List;

/**
 * Created by tri on 22/02/2017.
 */
public class ShoppingCardRepository implements ShoppingCardDataSource {
    private static ShoppingCardRepository sRepository;
    private ShoppingCardDataSource mLocalDataSource;

    private ShoppingCardRepository(ShoppingCardLocalDataSource shoppingCardDataSource) {
        mLocalDataSource = shoppingCardDataSource;
    }

    public static ShoppingCardRepository getInstance(Context context) {
        if (sRepository == null) {
            sRepository = new ShoppingCardRepository(ShoppingCardLocalDataSource
                .getInstance(context));
        }
        return sRepository;
    }

    @Override
    public void saveShoppingCard(ShoppingCard shoppingCard) {
        mLocalDataSource.saveShoppingCard(shoppingCard);
    }

    @Override
    public void getShoppingCard(int idDomain, final GetDataCallback<ShoppingCard> getDataCallback) {
        if (getDataCallback == null) return;
        mLocalDataSource.getShoppingCard(idDomain, new GetDataCallback<ShoppingCard>() {
            @Override
            public void onLoaded(List<ShoppingCard> datas) {
                getDataCallback.onLoaded(datas);
            }

            @Override
            public void onNotAvailable() {
                getDataCallback.onNotAvailable();
            }
        });
    }

    @Override
    public boolean isExistsDomainAndShop(ShoppingCard shoppingCard) {
        return mLocalDataSource.isExistsDomainAndShop(shoppingCard);
    }

    @Override
    public int totalPriceShoppingCard(int idDomain) {
        return mLocalDataSource.totalPriceShoppingCard(idDomain);
    }
}
