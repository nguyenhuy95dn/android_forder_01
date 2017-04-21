package com.framgia.forder.data.source.local;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShoppingCart;
import com.framgia.forder.data.source.ProductDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
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
                    if (realm.where(ShoppingCart.class).findAll().size() == 0) {
                        cart.setShoppingCartId(1);
                    } else {
                        cart.setShoppingCartId(
                                realm.where(ShoppingCart.class).max("mShoppingCartId").intValue()
                                        + 1);
                    }
                    cart.setDomainId(product.getShop().getDomainId());
                    cart.setShopId(product.getShopId());
                    cart.setQuantity(DEFAULT_QUANTITY);
                    cart.setProductId(product.getId());
                    cart.setPrice(product.getPrice());
                    cart.setShopName(product.getShop().getName());
                    cart.setProductName(product.getName());
                    cart.setProductImage(product.getCollectionImage().getImage().getUrl());
                    cart.setStartHour(product.getStartHour());
                    cart.setEndHour(product.getEndHour());
                    cart.setTotal(cart.getTotal());
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
    public Observable<List<Cart>> getAllShoppingCart(final int domainId) {
        return mRealmApi.realmGet(new Action2<Subscriber<? super List<Cart>>, Realm>() {
            @Override
            public void call(Subscriber<? super List<Cart>> subscriber, Realm realm) {
                List<Cart> cartList = new ArrayList<>();

                RealmResults<ShoppingCart> shop = realm.where(ShoppingCart.class)
                        .equalTo("mDomainId", domainId)
                        .distinct("mShopId");

                for (ShoppingCart shoppingCart : shop) {
                    Cart cart = new Cart(shoppingCart.getDomainId(), shoppingCart.getShopId(),
                            shoppingCart.getShopName(), shoppingCart.getTotal());
                    cartList.add(cart);
                }
                for (Cart cart : cartList) {
                    RealmResults<ShoppingCart> shopId = realm.where(ShoppingCart.class)
                            .equalTo("mShopId", cart.getShopId())
                            .findAll();
                    List<CartItem> cartItemList = new ArrayList<>();
                    for (ShoppingCart shoppingCart : shopId) {
                        CartItem cartItem =
                                new CartItem(shoppingCart.getDomainId(), shoppingCart.getShopId(),
                                        shoppingCart.getQuantity(), shoppingCart.getProductId(),
                                        shoppingCart.getProductName(),
                                        shoppingCart.getProductImage(), shoppingCart.getPrice(),
                                        shoppingCart.getStartHour(), shoppingCart.getEndHour());
                        cartItemList.add(cartItem);
                    }
                    cart.setCartItemList(cartItemList);
                }
                try {
                    subscriber.onNext(cartList);
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }
}
