package com.framgia.forder.data.source.local;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.DataSuggest;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.SearchDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;

/**
 * Created by Age on 4/28/2017.
 */

public class SearchLocalDataSource implements SearchDataSource.LocalDataSource {
    private RealmApi mRealmApi;

    public SearchLocalDataSource(RealmApi realmApi) {
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
    public Observable<Void> addShopDataSuggest(@NonNull final List<Shop> shops,
            @NonNull final int currentDomainId) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                RealmResults<DataSuggest> dataSuggest = realm.where(DataSuggest.class)
                        .equalTo("mDomainId", currentDomainId)
                        .findAll();
                try {
                    if (dataSuggest.size() == 0) {
                        for (Shop shop : shops) {
                            DataSuggest dataSuggest1 = new DataSuggest();
                            dataSuggest1.setName(shop.getName());
                            dataSuggest1.setDomainId(currentDomainId);
                            realm.insertOrUpdate(dataSuggest1);
                        }
                    } else {
                        for (DataSuggest suggest : dataSuggest) {
                            for (Shop shop : shops) {
                                if (shop.getName() != suggest.getName()) {
                                    DataSuggest dataSuggest1 = new DataSuggest();
                                    dataSuggest1.setName(shop.getName());
                                    dataSuggest1.setDomainId(currentDomainId);
                                    realm.insertOrUpdate(dataSuggest1);
                                }
                            }
                        }
                    }
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<DataSuggest>> getAllName(final int domainId) {
        return mRealmApi.realmGet(new Action2<Subscriber<? super List<DataSuggest>>, Realm>() {
            @Override
            public void call(Subscriber<? super List<DataSuggest>> subscriber, Realm realm) {
                List<DataSuggest> suggests = new ArrayList<DataSuggest>();
                RealmResults<DataSuggest> dataSuggest =
                        realm.where(DataSuggest.class).equalTo("mDomainId", domainId).findAll();
                suggests.addAll(dataSuggest);
                try {
                    subscriber.onNext(dataSuggest);
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Void> addProductDataSuggest(@NonNull final List<Product> products,
            @NonNull final int domainId) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                RealmResults<DataSuggest> dataSuggest =
                        realm.where(DataSuggest.class).equalTo("mDomainId", domainId).findAll();
                try {
                    if (dataSuggest.size() == 0) {
                        for (Product product : products) {
                            DataSuggest dataSuggest1 = new DataSuggest();
                            dataSuggest1.setName(product.getName());
                            dataSuggest1.setDomainId(domainId);
                            realm.insertOrUpdate(dataSuggest1);
                        }
                    } else {
                        for (DataSuggest suggest : dataSuggest) {
                            for (Product product : products) {
                                if (product.getName() != suggest.getName()) {
                                    DataSuggest dataSuggest1 = new DataSuggest();
                                    dataSuggest1.setName(product.getName());
                                    dataSuggest1.setDomainId(domainId);
                                    realm.insertOrUpdate(dataSuggest1);
                                }
                            }
                        }
                    }
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }
}
