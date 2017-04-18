package com.framgia.forder.data.source.local;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShoppingCart;
import com.framgia.forder.data.source.ProductDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;

import static com.framgia.forder.utils.Constant.DEFAULT_QUANTITY;

public class ProductLocalDataSource implements ProductDataSource.LocalDataSource {
    private RealmApi mRealmApi;

    public ProductLocalDataSource(RealmApi realmApi) {
        mRealmApi = realmApi;
    }

    @Override
    public void openTransaction() {
        if (mRealmApi == null) {
            mRealmApi = new RealmApi();
        }
    }

    @Override
    public void closeTransaction() {
        mRealmApi.closeRealmOnMainThread();
    }

    @Override
    public Observable<Void> addShoppingCard(@NonNull final Product product) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setIdShoppingCard(
                        realm.where(ShoppingCart.class).max("mIdShoppingCard").intValue() + 1);
                shoppingCart.setDomainId(product.getShop().getDomainId());
                shoppingCart.setShopId(product.getShop().getShopId());
                shoppingCart.setQuantity(DEFAULT_QUANTITY);
                shoppingCart.setProductId(product.getId());
                shoppingCart.setPrice(product.getPrice());
                shoppingCart.setShopName(product.getShop().getName());
                shoppingCart.setProductName(product.getName());
                shoppingCart.setProductImage(product.getCollectionImage().getImage().getUrl());
                shoppingCart.setStartHour(product.getStartHour());
                shoppingCart.setEndHour(product.getEndHour());
                try {
                    realm.insert(shoppingCart);
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<ShoppingCart>> getAllShoppingCart() {
        return mRealmApi.realmGet(new Action2<Subscriber<? super List<ShoppingCart>>, Realm>() {
            @Override
            public void call(Subscriber<? super List<ShoppingCart>> subscriber, Realm realm) {
                try {
                    RealmResults<ShoppingCart> carts = realm.where(ShoppingCart.class).findAll();
                    subscriber.onNext(carts);
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }
}
