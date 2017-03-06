package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.ShopManagement;

/**
 * Created by tri on 06/03/2017.
 */
public interface ShopDataSource {
    void getDataShop(int domainId, GetDataCallback<Shop> getDataCallback);
    void getDataShopManagement(int userID, String userToken,
                               GetDataCallback<ShopManagement> getDataCallback);
}
