package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.ProductDataSource;
import com.framgia.forder.data.source.remote.api.response.ProductResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Duong on 4/14/2017.
 */

public class ProductRemoteDataSource extends BaseRemoteDataSource
        implements ProductDataSource.RemoteDataSource {
    private final int START_SUB_LIST = 0;
    private final int END_SUB_LIST = 6;

    public ProductRemoteDataSource(FOrderApi FOrderApi) {
        super(FOrderApi);
    }

    @Override
    public Observable<List<Product>> getListProduct(int domainId) {
        return mFOrderApi.getListProduct(domainId)
                .flatMap(new Func1<ProductResponse, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            return Observable.just(productResponse.getListProduct());
                        }
                        return Observable.error(new NullPointerException());
                    }
                })
                .map(new Func1<List<Product>, List<Product>>() {
                    @Override
                    public List<Product> call(List<Product> products) {
                        if (products.size() <= END_SUB_LIST) {
                            return products;
                        }
                        return products.subList(START_SUB_LIST, END_SUB_LIST);
                    }
                });
    }
}
