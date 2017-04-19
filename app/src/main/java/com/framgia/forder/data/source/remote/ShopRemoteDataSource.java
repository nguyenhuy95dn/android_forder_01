package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.ShopDataSource;
import com.framgia.forder.data.source.remote.api.response.ProductResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Duong on 4/19/2017.
 */

public class ShopRemoteDataSource extends BaseRemoteDataSource
        implements ShopDataSource.RemoteDataSource {
    private final int START_SUB_LIST = 0;
    private final int END_SUB_LIST = 6;

    public ShopRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<List<Shop>> getListShop(int domainId) {
        final List<Shop> shops = new ArrayList<>();
        // todo edit later
        return mFOrderApi.getListShop(domainId)
                .flatMap(new Func1<ProductResponse, Observable<List<Shop>>>() {
                    @Override
                    public Observable<List<Shop>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            for (Product product : productResponse.getListProduct()) {
                                shops.add(product.getShop());
                            }
                            return Observable.just(shops);
                        }
                        return Observable.error(new NullPointerException());
                    }
                })
                .map(new Func1<List<Shop>, List<Shop>>() {
                    @Override
                    public List<Shop> call(List<Shop> list) {
                        if (list.size() <= END_SUB_LIST) {
                            return list;
                        }
                        return list.subList(START_SUB_LIST, END_SUB_LIST);
                    }
                });
    }
}
