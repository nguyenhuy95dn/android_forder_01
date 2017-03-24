package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.data.model.User;

/**
 * Created by tri on 06/03/2017.
 */
public interface ShopDataSource {
    void getDataShop(int domainId, User user, GetDataCallback<Shop> getDataCallback);
    void getDataShopManagement(User user,
                               GetDataCallback<ShopManagement> getDataCallback);
}
