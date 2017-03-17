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
        //Fake data
        Category category1 = new Category(1,"Nem");
        Category category2 = new Category(2,"Bún");
        Category category3 = new Category(3,"Chè");

        List<Product> list = new ArrayList<Product>();

        List<Shop> listShop = new ArrayList<>();
        String imageShop1 =
            "http://giaykiyomi.net/wp-content/uploads/2016/03/hinh-anh-cach-lam-che-thai-xanh-sua-dua-7.jpg";
        String imageShop2 =
            "http://img.diadiemanuong.com/review/43243/890191831_1729923611_574_574.jpg";
        String imageShop3 =
            "https://i.ytimg.com/vi/NQGHeVY-N4U/hqdefault.jpg";
        String imageShop4 =
            "http://resources.cungmua.com/FTPProduct/39227/he-thong-com-tam-cali-(11).jpg";
        String imageShop5 =
            "http://img.news.zing.vn/img/686/t686644.jpg";
        String imageShop6 =
            "http://i1119.photobucket.com/albums/k623/thangthanglongtour/webdulichhue/xoi-rom-hue" +
                "/hues-xoi-rom-58_zps90f21224.jpg";
        listShop.add(new Shop(2, "Bún thịt nướng", "Bún",
            new CollectionAvatar(new Avatar(imageShop2,
                new Standard(imageShop2), new Thumbnail(imageShop2))), list, 5));
        listShop.add(new Shop(3, "Chè thập cẩm", "Chè",
            new CollectionAvatar(new Avatar(imageShop1,
                new Standard(imageShop1), new Thumbnail(imageShop1))), list, 4));
        listShop.add(new Shop(1, "Nem chua rán", "Nem",
            new CollectionAvatar(new Avatar(imageShop3,
                new Standard(imageShop3), new Thumbnail(imageShop3))), list, 3));
        listShop.add(new Shop(4, "Cơm", "Cơm Tấm",
            new CollectionAvatar(new Avatar(imageShop4,
                new Standard(imageShop4), new Thumbnail(imageShop4))), list, 2));
        listShop.add(new Shop(5, "Nếp cẩm", "Chè Nếp cẩm",
            new CollectionAvatar(new Avatar(imageShop5,
                new Standard(imageShop5), new Thumbnail(imageShop5))), list, 4));
        listShop.add(new Shop(6, "Xôi", "Xôi gà",
            new CollectionAvatar(new Avatar(imageShop6,
                new Standard(imageShop6), new Thumbnail(imageShop6))), list, 4));

        Shop shop1 = new Shop(1, "Nem chua rán", "Nem",
            new CollectionAvatar(new Avatar(imageShop3,
                new Standard(imageShop3), new Thumbnail(imageShop3))), list,3);
        Shop shop2 = new Shop(2, "Bún thịt nướng", "Bún",
            new CollectionAvatar(new Avatar(imageShop1,
                new Standard(imageShop1), new Thumbnail(imageShop1))), list,5);
        Shop shop3 = new Shop(3, "Chè thập cẩm", "Chè",
            new CollectionAvatar(new Avatar(imageShop2,
                new Standard(imageShop2), new Thumbnail(imageShop2))), list,2);
        Shop shop4 = new Shop(4, "Cơm", "Cơm Tấm",
            new CollectionAvatar(new Avatar(imageShop4,
                new Standard(imageShop4), new Thumbnail(imageShop4))), list,4);
        Shop shop5 = new Shop(5, "Nếp cẩm", "Chè Nếp cẩm",
            new CollectionAvatar(new Avatar(imageShop5,
                new Standard(imageShop5), new Thumbnail(imageShop5))), list,1);
        Shop shop6 = new Shop(6, "Xôi", "Xôi gà",
            new CollectionAvatar(new Avatar(imageShop6,
                new Standard(imageShop6), new Thumbnail(imageShop6))), list,5);
        String image1 =
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS5vAH1Qjf01K8LSq0Hz_KHHsuZ2uRi3pm3kvrHblYcjMJkJDP8";
        String image2 =
            "http://anh.eva.vn/upload/4-2014/images/2014-11-18/1416283892-bun-thit-nuong-4.jpg";
        String image3 =
            "https://hd1.hotdeal.vn/hinhanh/HCM/84316_body_%20(13).jpg";
        String image4 =
            "http://cobavungtau.com/wp-content/uploads/2014/12/comtamsuoncha.jpg";
        String image5="http://anh.24h.com" +
            ".vn/upload/2-2013/images/2013-04-19/1366338095-lam-ruou-nep-cam-va-sua-chua-nep-cam.JPG";
        String image6 =
            "http://toinayangi.vn/wp-content/uploads/2014/08/xoi-ga-nuoc-cot-dua-6.jpg";

        list.add(new Product(1, "Nem Chua", 10000, "Nem chua rán nhỏ", new CollectionImage
            (new Image(image1, new Standard(image1), new Thumbnail(image1))),"12:00","13:00",
            shop1,category1));
        list.add(new Product(2, "Bún", 20000, "Bún Thịt nướng", new CollectionImage
            (new Image(image2, new Standard(image2), new Thumbnail(image2))),"10:00","11:00",
            shop2,category2));
        list.add(new Product(3, "Chè", 30000, "Chè thập cẩm", new CollectionImage
            (new Image(image3, new Standard(image3), new Thumbnail(image3))),"10:00","11:00",
            shop3,category3));
        list.add(new Product(4, "Cơm", 10000, "Cơm tấm", new CollectionImage
            (new Image(image4, new Standard(image4), new Thumbnail(image4))),"13:00","14:00",
            shop4,category1));
        list.add(new Product(5, "Chè Nếp cẩm", 20000, "Nếp cẩm", new CollectionImage
            (new Image(image5, new Standard(image5), new Thumbnail(image5))),"11:00","12:00",
            shop5,category2));
        list.add(new Product(6, "Xôi", 30000, "Xôi gà", new CollectionImage
            (new Image(image6, new Standard(image6), new Thumbnail(image6))),"10:00","11:00",
            shop6,category3));

        getDataCallback.onLoaded(listShop);
    }

    @Override
    public void getDataShopManagement(int userId, String userToken,
                                      final GetDataCallback<ShopManagement> getDataCallback) {
        if (getDataCallback == null) return;
        // Fake Data

        Category category1 = new Category(1,"Nem");
        Category category2 = new Category(2,"Bún");
        Category category3 = new Category(3,"Chè");

        List<Product> list = new ArrayList<Product>();

        String imageShop1 =
            "http://giaykiyomi.net/wp-content/uploads/2016/03/hinh-anh-cach-lam-che-thai-xanh-sua-dua-7.jpg";
        String imageShop2 =
            "http://img.diadiemanuong.com/review/43243/890191831_1729923611_574_574.jpg";
        String imageShop3 =
            "https://i.ytimg.com/vi/NQGHeVY-N4U/hqdefault.jpg";
        String imageShop4 =
            "http://resources.cungmua.com/FTPProduct/39227/he-thong-com-tam-cali-(11).jpg";
        String imageShop5 =
            "http://img.news.zing.vn/img/686/t686644.jpg";
        String imageShop6 =
            "http://i1119.photobucket.com/albums/k623/thangthanglongtour/webdulichhue/xoi-rom-hue" +
                "/hues-xoi-rom-58_zps90f21224.jpg";
        Shop shop2 = new Shop(2, "Bún thịt nướng", "Bún",
            new CollectionAvatar(new Avatar(imageShop2,
                new Standard(imageShop2), new Thumbnail(imageShop2))), list,1);
        Shop shop3 = new Shop(3, "Chè thập cẩm", "Chè",
            new CollectionAvatar(new Avatar(imageShop1,
                new Standard(imageShop1), new Thumbnail(imageShop1))), list,2);
        Shop shop1 = new Shop(1, "Nem chua rán", "Nem",
            new CollectionAvatar(new Avatar(imageShop3,
                new Standard(imageShop3), new Thumbnail(imageShop3))), list,3);
        Shop shop4 = new Shop(4, "Cơm", "Cơm Tấm",
            new CollectionAvatar(new Avatar(imageShop4,
                new Standard(imageShop4), new Thumbnail(imageShop4))), list,6);
        Shop shop5 = new Shop(5, "Nếp cẩm", "Chè Nếp cẩm",
            new CollectionAvatar(new Avatar(imageShop5,
                new Standard(imageShop5), new Thumbnail(imageShop5))), list,4);
        Shop shop6 = new Shop(6, "Xôi", "Xôi gà",
            new CollectionAvatar(new Avatar(imageShop6,
                new Standard(imageShop6), new Thumbnail(imageShop6))), list,3);

        String image1 =
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS5vAH1Qjf01K8LSq0Hz_KHHsuZ2uRi3pm3kvrHblYcjMJkJDP8";
        String image2 =
            "http://anh.eva.vn/upload/4-2014/images/2014-11-18/1416283892-bun-thit-nuong-4.jpg";
        String image3 =
            "https://hd1.hotdeal.vn/hinhanh/HCM/84316_body_%20(13).jpg";
        String image4 =
            "http://cobavungtau.com/wp-content/uploads/2014/12/comtamsuoncha.jpg";
        String image5="http://anh.24h.com" +
            ".vn/upload/2-2013/images/2013-04-19/1366338095-lam-ruou-nep-cam-va-sua-chua-nep-cam.JPG";
        String image6 =
            "http://toinayangi.vn/wp-content/uploads/2014/08/xoi-ga-nuoc-cot-dua-6.jpg";

        list.add(new Product(1, "Nem Chua", 10000, "Nem chua rán nhỏ", new CollectionImage
            (new Image(image1, new Standard(image1), new Thumbnail(image1))),"12:00","13:00",
            shop1,category1));
        list.add(new Product(2, "Bún", 20000, "Bún Thịt nướng", new CollectionImage
            (new Image(image2, new Standard(image2), new Thumbnail(image2))),"10:00","11:00",
            shop2,category1));
        list.add(new Product(3, "Chè", 30000, "Chè thập cẩm", new CollectionImage
            (new Image(image3, new Standard(image3), new Thumbnail(image3))),"10:00","11:00",
            shop3,category1));
        list.add(new Product(4, "Cơm", 10000, "Cơm tấm", new CollectionImage
            (new Image(image4, new Standard(image4), new Thumbnail(image4))),"13:00","14:00",
            shop4,category1));
        list.add(new Product(5, "Nếp cẩm", 20000, "Nếp cẩm", new CollectionImage
            (new Image(image5, new Standard(image5), new Thumbnail(image5))),"11:00","12:00",
            shop5,category1));
        list.add(new Product(6, "Xôi", 30000, "Xôi gà", new CollectionImage
            (new Image(image6, new Standard(image6), new Thumbnail(image6))),"10:00","11:00",
            shop6,category1));

        List<ShopDomain> shopDomains = new ArrayList<>();
        shopDomains.add(new ShopDomain(1, 0));
        shopDomains.add(new ShopDomain(2, 1));
        shopDomains.add(new ShopDomain(3, 0));
        shopDomains.add(new ShopDomain(4, 2));

        List<ShopInfo> shopInfos = new ArrayList<>();
        shopInfos.add(new ShopInfo(1, "Đà Nẵng", 10, 10, 20));
        shopInfos.add(new ShopInfo(2, "Hà Nội", 20, 40, 20));
        shopInfos.add(new ShopInfo(3, "HCM", 30, 10, 20));
        shopInfos.add(new ShopInfo(4, "Đà Nẵng", 10, 10, 20));

        List<ShopManagement> shopManagements = new ArrayList<>();
        shopManagements.add(new ShopManagement(shop1, shopDomains, shopInfos));
        shopManagements.add(new ShopManagement(shop2, shopDomains, shopInfos));
        shopManagements.add(new ShopManagement(shop3, shopDomains, shopInfos));
        shopManagements.add(new ShopManagement(shop4, shopDomains, shopInfos));
        shopManagements.add(new ShopManagement(shop5, shopDomains, shopInfos));
        shopManagements.add(new ShopManagement(shop6, shopDomains, shopInfos));

        getDataCallback.onLoaded(shopManagements);
    }
}
