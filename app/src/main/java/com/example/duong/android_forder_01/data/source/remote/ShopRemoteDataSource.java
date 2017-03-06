package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Avatar;
import com.example.duong.android_forder_01.data.model.CollectionAvatar;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.ShopDomain;
import com.example.duong.android_forder_01.data.model.ShopInfo;
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.data.model.Standard;
import com.example.duong.android_forder_01.data.model.Thumbnail;
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
        //Fake data
        String image =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQx8JBEx8Ci_Q4LEU-crZKZz1tFQoumDiSC2y4l42UiWbCs1AVeIg";
        List<Shop> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(
                new Shop(1, "Bún thịt nướng", "Bún",
                    new CollectionAvatar(new Avatar(image,
                        new Standard(image), new Thumbnail(image)))));
        }
        getDataCallback.onLoaded(list);
    }

    @Override
    public void getDataShopManagement(int userId, String userToken,
                                      final GetDataCallback<ShopManagement> getDataCallback) {
        if (getDataCallback == null) return;
        // Fake Data
        String image =
            "http://giaykiyomi.net/wp-content/uploads/2016/03/hinh-anh-cach-lam-che-thai-xanh-sua-dua-7.jpg";
        Shop shop = new Shop(1, "Bún thịt nướng", "Bún",
            new CollectionAvatar(new Avatar(image,
                new Standard(image), new Thumbnail(image))));
        List<ShopDomain> shopDomains = new ArrayList<>();
        shopDomains.add(new ShopDomain(1, true));
        shopDomains.add(new ShopDomain(2, false));
        shopDomains.add(new ShopDomain(3, true));
        shopDomains.add(new ShopDomain(4, false));
        List<ShopInfo> shopInfos = new ArrayList<>();
        shopInfos.add(new ShopInfo(1, "Da nang", 10, 10, 20));
        shopInfos.add(new ShopInfo(2, "Ha Noi", 10, 10, 20));
        shopInfos.add(new ShopInfo(3, "HCM", 10, 10, 20));
        shopInfos.add(new ShopInfo(4, "Da nang", 10, 10, 20));
        List<ShopManagement> shopManagements = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            shopManagements.add(new ShopManagement(shop, shopDomains, shopInfos));
        }
        getDataCallback.onLoaded(shopManagements);
    }
}
