package com.example.duong.android_forder_01.data.model.source.local;

import android.content.Context;

import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.model.ShoppingCardDetail;
import com.example.duong.android_forder_01.data.model.source.GetDataCallback;
import com.example.duong.android_forder_01.data.model.source.ShoppingCardDetailDataSource;

/**
 * Created by tri on 22/02/2017.
 */
public class ShoppingCardDetailLocalDataSource extends DataHelper implements
    ShoppingCardDetailDataSource {
    private static ShoppingCardDetailLocalDataSource sLocalDataSource;

    public ShoppingCardDetailLocalDataSource(Context context) {
        super(context);
    }

    public static ShoppingCardDetailLocalDataSource getInstance(Context context) {
        if (sLocalDataSource == null) {
            sLocalDataSource = new ShoppingCardDetailLocalDataSource(context);
        }
        return sLocalDataSource;
    }

    @Override
    public void saveShoppingCardDetail(ShoppingCardDetail shoppingCardDetail, int idShoppingCard) {
        // TODO save shopping card detail
    }

    @Override
    public void getShoppingCardDetail(int idShoppingCard,
                                      GetDataCallback<ShoppingCardDetail> getDataCallback) {
        // TODO get shopping card detail
    }

    @Override
    public void deleteShoppingCardDetail(int idProduct, ShoppingCard shoppingCard) {
        // TODO delete shopping card item
    }

    @Override
    public boolean isExistsProduct(int idProduct) {
        return false;
        // TODO check id product
    }

    @Override
    public void downQuantity(int idShoppingCard, int idProduct) {
        // TODO down quantity of product
    }

    @Override
    public void upQuantity(int idShoppingCard, int idProduct) {
        // TODO up quantity of product
    }
}
