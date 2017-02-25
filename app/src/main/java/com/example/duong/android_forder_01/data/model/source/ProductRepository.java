package com.example.duong.android_forder_01.data.model.source;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.source.remote.ProductRemoteDataSource;

import java.util.List;

/**
 * Created by tri on 24/02/2017.
 */
public class ProductRepository implements DataSource<Product> {
    private static ProductRepository sProductRepository;
    private DataSource mRemoteDataSource;

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
    public void getDatas(final int idDomain, final GetDataCallback<Product> getDataCallback) {
        mRemoteDataSource.getDatas(idDomain, new GetDataCallback<Product>() {
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
