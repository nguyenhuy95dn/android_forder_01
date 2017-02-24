package com.example.duong.android_forder_01.data.model.source;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.source.remote.ShopRemoteDataSource;

import java.util.List;

/**
 * Created by tri on 24/02/2017.
 */
public class ShopReposity implements DataSource<Shop> {
    private static ShopReposity sShopReposity;
    private DataSource mRemoteDataSource;

    private ShopReposity(ShopRemoteDataSource shopRemoteDataSource) {
        mRemoteDataSource = shopRemoteDataSource;
    }

    public static ShopReposity getInstance() {
        if (sShopReposity == null) {
            sShopReposity = new ShopReposity(ShopRemoteDataSource.getInstance());
        }
        return sShopReposity;
    }

    @Override
    public void getDatas(int idDomain, final GetDataCallback<Shop> getDataCallback) {
        mRemoteDataSource.getDatas(idDomain, new GetDataCallback<Shop>() {
            @Override
            public void onLoaded(List<Shop> datas) {
                getDataCallback.onLoaded(datas);
            }

            @Override
            public void onNotAvailable() {
                getDataCallback.onNotAvailable();
            }
        });
    }
}
