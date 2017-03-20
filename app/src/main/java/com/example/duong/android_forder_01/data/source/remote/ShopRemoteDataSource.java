package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Avatar;
import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.CollectionAvatar;
import com.example.duong.android_forder_01.data.model.CollectionImage;
import com.example.duong.android_forder_01.data.model.Image;
import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.ShopDomain;
import com.example.duong.android_forder_01.data.model.ShopInfo;
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.data.model.Standard;
import com.example.duong.android_forder_01.data.model.Thumbnail;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.GetDataCallback;
import com.example.duong.android_forder_01.data.source.ShopDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 24/02/2017.
 */
public class ShopRemoteDataSource implements ShopDataSource {
    private static ShopRemoteDataSource sShopRemoteDataSource;

    private ShopRemoteDataSource() {
    }

    public static ShopRemoteDataSource getInstance() {
        if (sShopRemoteDataSource == null) {
            sShopRemoteDataSource = new ShopRemoteDataSource();
        }
        return sShopRemoteDataSource;
    }

    @Override
    public void getDataShop(int idDomain, GetDataCallback<Shop> getDataCallback) {
        if (getDataCallback == null) return;
    }

    @Override
    public void getDataShopManagement(int userId, String userToken,
                                      final GetDataCallback<ShopManagement> getDataCallback) {
        if (getDataCallback == null) return;
    }
}
