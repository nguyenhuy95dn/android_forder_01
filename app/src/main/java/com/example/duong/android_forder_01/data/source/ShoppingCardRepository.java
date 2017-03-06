package com.example.duong.android_forder_01.data.source;

import android.content.Context;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.source.local.ShoppingCardLocalDataSource;

import java.util.List;

public class ShoppingCardRepository
    implements ShoppingCardDataSource {
    private static ShoppingCardRepository sRepository;
    private ShoppingCardDataSource mLocalDataSource;

    private ShoppingCardRepository(ShoppingCardLocalDataSource
                                       shoppingCardDetailLocalDataSource) {
        mLocalDataSource = shoppingCardDetailLocalDataSource;
    }

    public static ShoppingCardRepository getInstance(Context context) {
        if (sRepository == null) {
            sRepository = new ShoppingCardRepository
                (ShoppingCardLocalDataSource.getInstance(context));
        }
        return sRepository;
    }

    @Override
    public void addShoppingCardItem(Product product, int domainId) {
        mLocalDataSource.addShoppingCardItem(product, domainId);
    }

    @Override
    public void deleteShoppingCardItem(int productId, int domainId) {
        mLocalDataSource.deleteShoppingCardItem(productId, domainId);
    }

    @Override
    public void reduceQuantity(int productId, int domainId) {
        mLocalDataSource.reduceQuantity(productId, domainId);
    }

    @Override
    public void increaseQuantity(int productId, int domainId) {
        mLocalDataSource.increaseQuantity(productId, domainId);
    }

    @Override
    public void getShoppingCard(int domainId,
                                final GetShoppingCardCallback callback) {
        mLocalDataSource.getShoppingCard(domainId, new GetShoppingCardCallback() {
            @Override
            public void onLoaded(List<ShoppingCard> list, double totalPrice) {
                callback.onLoaded(list, totalPrice);
            }

            @Override
            public void onLoadedError() {
                callback.onLoadedError();
            }
        });
    }
}
