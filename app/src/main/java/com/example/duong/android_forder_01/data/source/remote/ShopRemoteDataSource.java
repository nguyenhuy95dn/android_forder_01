package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Avatar;
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
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS5vAH1Qjf01K8LSq0Hz_KHHsuZ2uRi3pm3kvrHblYcjMJkJDP8";
        String image2 =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQx8JBEx8Ci_Q4LEU-crZKZz1tFQoumDiSC2y4l42UiWbCs1AVeIg";
        List<Shop> list = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(new Product(1, "Nem Chua", 10000, "Nem chua rán nhỏ",
                new CollectionImage(new Image(image, new Standard(image), new Thumbnail(image))),
                new Shop(1, "Nem", "Nem Chua", new CollectionAvatar(
                    new Avatar(image2, new Standard(image2), new Thumbnail(image2))))));
            list.add(new Shop(1, "Bún thịt nướng", "Bún",
                new CollectionAvatar(new Avatar(image, new Standard(image), new Thumbnail(image))),
                products));
        }
        getDataCallback.onLoaded(list);
    }

    @Override
    public void getDataShopManagement(int userId, String userToken,
                                      final GetDataCallback<ShopManagement> getDataCallback) {
        if (getDataCallback == null) return;
        // Fake Data
        String image1 =
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS5vAH1Qjf01K8LSq0Hz_KHHsuZ2uRi3pm3kvrHblYcjMJkJDP8";
        String image2 =
            "http://anh.eva.vn/upload/4-2014/images/2014-11-18/1416283892-bun-thit-nuong-4.jpg";
        String image3 =
            "https://hd1.hotdeal.vn/hinhanh/HCM/84316_body_%20(13).jpg";
        List<Product> list = new ArrayList<Product>();
        list.add(new Product(1, "Nem Chua", 10000, "Nem chua rán nhỏ", new CollectionImage
            (new Image(image1, new Standard(image1), new Thumbnail(image1)))));
        list.add(new Product(2, "Bún", 20000, "Bún Thịt nướng", new CollectionImage
            (new Image(image2, new Standard(image2), new Thumbnail(image2)))));
        list.add(new Product(3, "Chè", 30000, "Chè thập cẩm", new CollectionImage
            (new Image(image3, new Standard(image3), new Thumbnail(image3)))));
        list.add(new Product(4, "Nem Chua", 10000, "Nem chua rán nhỏ", new CollectionImage
            (new Image(image1, new Standard(image1), new Thumbnail(image1)))));
        list.add(new Product(5, "Bún", 20000, "Bún Thịt nướng", new CollectionImage
            (new Image(image2, new Standard(image2), new Thumbnail(image2)))));
        list.add(new Product(6, "Chè", 30000, "Chè thập cẩm", new CollectionImage
            (new Image(image3, new Standard(image3), new Thumbnail(image3)))));
        String image =
            "http://giaykiyomi.net/wp-content/uploads/2016/03/hinh-anh-cach-lam-che-thai-xanh-sua-dua-7.jpg";
        String image4 =
            "https://media.foody.vn/res/g16/150824/prof/s480x300/foody-mobile-che2-jpg-834-635723946213556865.jpg";
        String image5 =
            "https://i.ytimg.com/vi/NQGHeVY-N4U/hqdefault.jpg";
        Shop shop = new Shop(1, "Bún thịt nướng", "Bún",
            new CollectionAvatar(new Avatar(image,
                new Standard(image), new Thumbnail(image))), list);
        Shop shop1 = new Shop(2, "Chè thập cẩm", "Chè",
            new CollectionAvatar(new Avatar(image4,
                new Standard(image4), new Thumbnail(image4))), list);
        Shop shop2 = new Shop(3, "Nem chua rán", "Nem",
            new CollectionAvatar(new Avatar(image5,
                new Standard(image5), new Thumbnail(image5))), list);
        List<ShopDomain> shopDomains = new ArrayList<>();
        shopDomains.add(new ShopDomain(1, true));
        shopDomains.add(new ShopDomain(2, false));
        shopDomains.add(new ShopDomain(3, true));
        shopDomains.add(new ShopDomain(4, false));
        List<ShopInfo> shopInfos = new ArrayList<>();
        shopInfos.add(new ShopInfo(1, "Đà Nẵng", 10, 10, 20));
        shopInfos.add(new ShopInfo(2, "Ha Noi", 20, 40, 20));
        shopInfos.add(new ShopInfo(3, "HCM", 30, 10, 20));
        shopInfos.add(new ShopInfo(4, "Đà Nẵng", 10, 10, 20));
        List<ShopManagement> shopManagements = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            shopManagements.add(new ShopManagement(shop, shopDomains, shopInfos));
            shopManagements.add(new ShopManagement(shop1, shopDomains, shopInfos));
            shopManagements.add(new ShopManagement(shop2, shopDomains, shopInfos));
        }
        getDataCallback.onLoaded(shopManagements);
    }
}
