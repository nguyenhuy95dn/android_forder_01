package com.example.duong.android_forder_01.ui.shoppingcard;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.source.ShoppingCardDataSource;

import java.util.List;

public class ShoppingCardPresenter implements ShoppingCardContract.Presenter {
    private ShoppingCardContract.View mShoppingCardView;
    private ShoppingCardDataSource mShoppingCardDataSource;

    public ShoppingCardPresenter(@NonNull ShoppingCardContract.View shoppingCardView,
                                 ShoppingCardDataSource shoppingCardDataSource) {
        mShoppingCardView = shoppingCardView;
        shoppingCardView.setPresenter(this);
        mShoppingCardDataSource = shoppingCardDataSource;
    }

    @Override
    public void start() {
        mShoppingCardView.start();
    }

    @Override
    public void loadShoppingCard(int domainId) {
        mShoppingCardDataSource.getShoppingCard(domainId,
            new ShoppingCardDataSource.GetShoppingCardCallback() {
                @Override
                public void onLoaded(List<ShoppingCard> list, double totalPrice) {
                    mShoppingCardView.loadShoppingCardCompleted(list, totalPrice);
                }

                @Override
                public void onLoadedError() {
                    mShoppingCardView.loadShoppingCardError();
                }
            });
    }

    @Override
    public void reduceQuantity(int productId, int domainId) {
        mShoppingCardDataSource.reduceQuantity(productId, domainId);
        loadShoppingCard(domainId);
    }

    @Override
    public void increaseQuantity(int productId, int domainId) {
        mShoppingCardDataSource.increaseQuantity(productId, domainId);
        loadShoppingCard(domainId);
    }

    @Override
    public void deleteItem(int productId, int domainId) {
        mShoppingCardDataSource.deleteShoppingCardItem(productId, domainId);
        loadShoppingCard(domainId);
    }

    @Override
    public void order(ShoppingCard shoppingCard) {
        // TODO order theo shop
    }

    @Override
    public void orderAll(List<ShoppingCard> list) {
        // TODO order tat ca
    }
}
