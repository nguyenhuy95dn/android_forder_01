package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Avatar;
import com.example.duong.android_forder_01.data.model.CollectionAvatar;
import com.example.duong.android_forder_01.data.model.CollectionImage;
import com.example.duong.android_forder_01.data.model.Image;
import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.Standard;
import com.example.duong.android_forder_01.data.model.Thumbnail;
import com.example.duong.android_forder_01.data.source.DataSource;
import com.example.duong.android_forder_01.data.source.GetDataCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 24/02/2017.
 */
public class ProductRemoteDataSource implements DataSource<Product> {
    private static ProductRemoteDataSource sProductRemoteDataSource;

    private ProductRemoteDataSource() {
    }

    public static ProductRemoteDataSource getInstance() {
        if (sProductRemoteDataSource == null) {
            sProductRemoteDataSource = new ProductRemoteDataSource();
        }
        return sProductRemoteDataSource;
    }

    @Override
    public void getDatas(final int idDomain,
                         final GetDataCallback<Product> getDataCallback) {
        if (getDataCallback == null) return;
        //Fake data
        String image =
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS5vAH1Qjf01K8LSq0Hz_KHHsuZ2uRi3pm3kvrHblYcjMJkJDP8";
        String image2 =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQx8JBEx8Ci_Q4LEU-crZKZz1tFQoumDiSC2y4l42UiWbCs1AVeIg";
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < 10; i++) {
            list.add(new Product(1, "Nem Chua", 10000, "Nem chua rán nhỏ", new CollectionImage
                (new
                    Image(image, new Standard(image), new Thumbnail(image))),
                new Shop(1,
                    "Nem", "Nem Chua", new CollectionAvatar(new Avatar(image2,
                    new Standard(image2), new Thumbnail(image2))))));
        }
        getDataCallback.onLoaded(list);
    }

    @Override
    public void getCategoryById(int idCategory,
                                GetDataCallback<Product> getDataCallback) {
        if (getDataCallback == null) return;
        // TODO get id Domain from sharedpreference
        //fake data
        String image =
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS5vAH1Qjf01K8LSq0Hz_KHHsuZ2uRi3pm3kvrHblYcjMJkJDP8";
        String image2 =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQx8JBEx8Ci_Q4LEU-crZKZz1tFQoumDiSC2y4l42UiWbCs1AVeIg";
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < 10; i++) {
            list.add(new Product(1, "Nem Chua", 10000, "Nem chua rán nhỏ", new CollectionImage
                (new
                    Image(image, new Standard(image), new Thumbnail(image))),
                new Shop(1,
                    "Nem", "Nem Chua", new CollectionAvatar(new Avatar(image2,
                    new Standard(image2), new Thumbnail(image2))))));
        }
        getDataCallback.onLoaded(list);
    }
}
