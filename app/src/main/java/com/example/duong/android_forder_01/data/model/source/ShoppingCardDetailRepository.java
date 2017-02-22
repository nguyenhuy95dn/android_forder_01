package com.example.duong.android_forder_01.data.model.source;

import android.content.Context;

import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.model.ShoppingCardDetail;
import com.example.duong.android_forder_01.data.model.source.local.ShoppingCardDetailLocalDataSource;

import java.util.List;

/**
 * Created by tri on 22/02/2017.
 */
public class ShoppingCardDetailRepository
    implements ShoppingCardDetailDataSource {
    private static ShoppingCardDetailRepository sRepository;
    private ShoppingCardDetailDataSource mLocalDataSource;

    private ShoppingCardDetailRepository(ShoppingCardDetailLocalDataSource
                                             shoppingCardDetailLocalDataSource) {
        mLocalDataSource = shoppingCardDetailLocalDataSource;
    }

    public static ShoppingCardDetailRepository getInstance(Context context) {
        if (sRepository == null) {
            sRepository = new ShoppingCardDetailRepository
                (ShoppingCardDetailLocalDataSource.getInstance(context));
        }
        return sRepository;
    }

    @Override
    public void saveShoppingCardDetail(ShoppingCardDetail shoppingCardDetail, int idShoppingCard) {
        mLocalDataSource.saveShoppingCardDetail(shoppingCardDetail, idShoppingCard);
    }

    @Override
    public void getShoppingCardDetail(int idShoppingCard,
                                      final GetDataCallback<ShoppingCardDetail> getDataCallback) {
        if (getDataCallback == null) return;
        mLocalDataSource
            .getShoppingCardDetail(idShoppingCard, new GetDataCallback<ShoppingCardDetail>() {
                @Override
                public void onLoaded(List<ShoppingCardDetail> datas) {
                    getDataCallback.onLoaded(datas);
                }

                @Override
                public void onNotAvailable() {
                    getDataCallback.onNotAvailable();
                }
            });
    }

    @Override
    public void deleteShoppingCardDetail(int idProduct, ShoppingCard shoppingCard) {
        mLocalDataSource.deleteShoppingCardDetail(idProduct, shoppingCard);
    }

    @Override
    public boolean isExistsProduct(int idProduct) {
        return mLocalDataSource.isExistsProduct(idProduct);
    }

    @Override
    public void downQuantity(int idShoppingCard, int idProduct) {
        mLocalDataSource.downQuantity(idShoppingCard, idProduct);
    }

    @Override
    public void upQuantity(int idShoppingCard, int idProduct) {
        mLocalDataSource.upQuantity(idShoppingCard, idProduct);
    }
}
