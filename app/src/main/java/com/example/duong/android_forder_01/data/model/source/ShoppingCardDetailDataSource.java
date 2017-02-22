package com.example.duong.android_forder_01.data.model.source;

import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.model.ShoppingCardDetail;

/**
 * Created by tri on 22/02/2017.
 */
public interface ShoppingCardDetailDataSource {
    void saveShoppingCardDetail(ShoppingCardDetail shoppingCardDetail, int idShoppingCard);
    void getShoppingCardDetail(int idShoppingCard, GetDataCallback<ShoppingCardDetail> getCallback);
    void deleteShoppingCardDetail(int idProduct, ShoppingCard shoppingCard);
    boolean isExistsProduct(int idProduct);
    void downQuantity(int idShoppingCard, int idProduct);
    void upQuantity(int idShoppingCard, int idProduct);
}
