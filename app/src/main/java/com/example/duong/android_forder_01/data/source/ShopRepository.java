package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.source.remote.ShopRemoteDataSource;

import java.util.List;

/**
 * Created by tri on 24/02/2017.
 */
public class ShopRepository implements DataSource<Shop> {
    private static ShopRepository sShopReposity;
    private DataSource mRemoteDataSource;

    private ShopRepository(ShopRemoteDataSource shopRemoteDataSource) {
        mRemoteDataSource = shopRemoteDataSource;
    }

    public static ShopRepository getInstance() {
        if (sShopReposity == null) {
            sShopReposity = new ShopRepository(ShopRemoteDataSource.getInstance());
        }
        return sShopReposity;
    }

    @Override
    public void getDatas(int domainId, final GetDataCallback<Shop> getDataCallback) {
        mRemoteDataSource.getDatas(domainId, new GetDataCallback<Shop>() {
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

    @Override
    public void getCategoryById(int categoryId,
                                GetDataCallback<Shop> getDataCallback) {
        // not required
    }
}
