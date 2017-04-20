package com.framgia.forder.data.source.local;

import android.support.annotation.NonNull;
import android.util.Log;
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
                ShoppingCart shoppingCart = realm.where(ShoppingCart.class)
                        .equalTo("mDomainId", product.getShop().getDomainId())
                        .equalTo("mProductId", product.getId())
                        .findFirst();
                if (shoppingCart == null) {
                    ShoppingCart cart = new ShoppingCart();
                    cart.setShoppingCartId(
                            realm.where(ShoppingCart.class).max("mShoppingCartId").intValue() + 1);
                    cart.setDomainId(product.getShop().getDomainId());
                    cart.setShopId(product.getShop().getShopId());
                    cart.setQuantity(DEFAULT_QUANTITY);
                    cart.setProductId(product.getId());
                    cart.setPrice(product.getPrice());
                    cart.setShopName(product.getShop().getName());
                    cart.setProductName(product.getName());
                    cart.setProductImage(product.getCollectionImage().getImage().getUrl());
                    cart.setStartHour(product.getStartHour());
                    cart.setEndHour(product.getEndHour());
                    try {
                        realm.insertOrUpdate(cart);
                        subscriber.onCompleted();
                    } catch (IllegalStateException e) {
                        subscriber.onError(e);
                    }
                } else {
                    try {
                        shoppingCart.setQuantity(shoppingCart.getQuantity() + 1);
                        realm.insertOrUpdate(shoppingCart);
                        subscriber.onCompleted();
                    } catch (IllegalStateException e) {
                        subscriber.onError(e);
                    }
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Void> deleteShoppingCard(@NonNull final int productId,
            @NonNull final int domainId) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                ShoppingCart cart = realm.where(ShoppingCart.class)
                        .equalTo("mDomainId", domainId)
                        .equalTo("mProductId", productId)
                        .findFirst();
                try {
                    cart.deleteFromRealm();
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Void> upQuantity(@NonNull final int productId, @NonNull final int domainId) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                ShoppingCart cart = realm.where(ShoppingCart.class)
                        .equalTo("mProductId", productId)
                        .equalTo("mDomainId", domainId)
                        .findFirst();
                try {
                    cart.setQuantity(cart.getQuantity() + 1);
                    realm.insertOrUpdate(cart);
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Void> downQuantity(@NonNull final int productId,
            @NonNull final int domainId) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                ShoppingCart cart = realm.where(ShoppingCart.class)
                        .equalTo("mProductId", productId)
                        .equalTo("mDomainId", domainId)
                        .findFirst();
                if (cart.getQuantity() == DEFAULT_QUANTITY) {
                    deleteShoppingCard(productId, domainId);
                    return;
                } else {
                    try {
                        cart.setQuantity(cart.getQuantity() - 1);
                        realm.insertOrUpdate(cart);
                        subscriber.onCompleted();
                    } catch (IllegalStateException e) {
                        subscriber.onError(e);
                    }
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Integer> getNumberItem(@NonNull final int domainId) {
        return mRealmApi.realmGet(new Action2<Subscriber<? super Integer>, Realm>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber, Realm realm) {
                try {
                    RealmResults<ShoppingCart> carts = realm.where(ShoppingCart.class)
                            .equalTo("mDomainId", domainId)
                            .findAll();
                    subscriber.onNext(carts.size());
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Double> getTotalPrice(@NonNull final int domainId) {
        return mRealmApi.realmGet(new Action2<Subscriber<? super Double>, Realm>() {
            @Override
            public void call(Subscriber<? super Double> subscriber, Realm realm) {
                try {
                    RealmResults<ShoppingCart> carts = realm.where(ShoppingCart.class)
                            .equalTo("mDomainId", domainId)
                            .findAll();
                    subscriber.onNext(carts.sum("mTotal").doubleValue());
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
