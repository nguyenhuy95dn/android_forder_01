package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.remote.ProductRemoteDataSource;

import java.util.List;

/**
 * Created by tri on 24/02/2017.
 */
public class ProductRepository implements ProductDataSource<Product> {
    private static ProductRepository sProductRepository;
    private ProductDataSource mRemoteDataSource;

    private ProductRepository(ProductRemoteDataSource productRemoteDataSource) {
        mRemoteDataSource = productRemoteDataSource;
    }

    public static ProductRepository getInstance() {
        if (sProductRepository == null) {
            sProductRepository = new ProductRepository(ProductRemoteDataSource.getInstance());
        }
        return sProductRepository;
    }

    @Override
    public void getDatas(final int domainId, final User user, final GetDataCallback<Product>
        getDataCallback) {
        if (getDataCallback == null && user == null) return;
        mRemoteDataSource.getDatas(domainId, user, new GetDataCallback<Product>() {
            @Override
            public void onLoaded(List<Product> datas) {
                getDataCallback.onLoaded(datas);
            }

            @Override
            public void onNotAvailable() {
                getDataCallback.onNotAvailable();
            }
        });
    }

    @Override
    public void getProductByCategoryId(final int domainId, final int categoryId, final User user,
                                       final GetDataCallback<Product> getDataCallback) {
        if (getDataCallback == null && user == null) return;
        mRemoteDataSource
            .getProductByCategoryId(domainId, categoryId, user, new GetDataCallback<Product>() {
                @Override
                public void onLoaded(List<Product> datas) {
                    getDataCallback.onLoaded(datas);
                }

                @Override
                public void onNotAvailable() {
                    getDataCallback.onNotAvailable();
                }
            });
    }
}
