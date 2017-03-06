package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.data.source.remote.ShopRemoteDataSource;

import java.util.List;

/**
 * Created by tri on 24/02/2017.
 */
public class ShopRepository implements ShopDataSource {
    private static ShopRepository sShopReposity;
    private ShopDataSource mRemoteDataSource;

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
    public void getDataShop(int domainId,
                            final GetDataCallback<Shop> getDataCallback) {
        mRemoteDataSource.getDataShop(domainId, new GetDataCallback<Shop>() {
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
    public void getDataShopManagement(int userId, String userToken,
                                      final GetDataCallback<ShopManagement> getDataCallback) {
        mRemoteDataSource.getDataShopManagement(userId, userToken,
            new GetDataCallback<ShopManagement>() {
                @Override
                public void onLoaded(List<ShopManagement> datas) {
                    getDataCallback.onLoaded(datas);
                }

                @Override
                public void onNotAvailable() {
                    getDataCallback.onNotAvailable();
                }
            });
    }
}
