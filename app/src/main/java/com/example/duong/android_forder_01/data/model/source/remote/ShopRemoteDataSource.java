package com.example.duong.android_forder_01.data.model.source.remote;

import com.example.duong.android_forder_01.data.model.Avatar;
import com.example.duong.android_forder_01.data.model.CollectionAvatar;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.Standard;
import com.example.duong.android_forder_01.data.model.Thumbnail;
import com.example.duong.android_forder_01.data.model.source.DataSource;
import com.example.duong.android_forder_01.data.model.source.GetDataCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 24/02/2017.
 */
public class ShopRemoteDataSource implements DataSource<Shop> {
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
    public void getDatas(int idDomain, GetDataCallback<Shop> getDataCallback) {
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
}
