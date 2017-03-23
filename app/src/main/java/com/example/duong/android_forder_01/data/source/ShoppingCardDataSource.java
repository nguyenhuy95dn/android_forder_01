package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.ShoppingCard;

import java.util.List;

public interface ShoppingCardDataSource {
    void addShoppingCardItem(Product product, int domainId);
    void deleteShoppingCardItem(int productId, int domainId);
    void deleteShoppingCardOfDomain(int domainId);
    void reduceQuantity(int productId, int domainId);
    void increaseQuantity(int productId, int domainId);
    void getShoppingCard(int domainId, GetShoppingCardCallback callback);
    int getNumberItem(int domainId);
    void deleteAllItemOutOfTime(List<ShoppingCard> list, int domainId,
                                DeleteAllItemOutOfTimeCardCallback cardCallback);
    interface DeleteAllItemOutOfTimeCardCallback {
        void onCompleted(List<String> list, double totalPrice);
    }

    interface GetShoppingCardCallback {
        void onLoaded(List<ShoppingCard> list, double totalPrice);
        void onLoadedError();
    }
}
