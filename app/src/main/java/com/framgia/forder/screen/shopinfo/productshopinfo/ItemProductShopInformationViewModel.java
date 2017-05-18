package com.framgia.forder.screen.shopinfo.productshopinfo;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Product;

/**
 * Created by levutantuan on 5/18/17.
 */

public class ItemProductShopInformationViewModel extends BaseObservable {

    private final Product mProduct;

    ItemProductShopInformationViewModel(Product product) {
        mProduct = product;
    }

    public String getProductImage() {
        if (mProduct != null
                && mProduct.getCollectionImage() != null
                && mProduct.getCollectionImage().getImage() != null) {
            return mProduct.getCollectionImage().getImage().getUrl();
        }
        return "";
    }

    public String getProductName() {
        return mProduct.getName();
    }

    public String getOrderTime() {
        return mProduct.getFormatStartHour() + "-" + mProduct.getFormatEndHour();
    }

    public String getProductPrice() {
        return mProduct.getFormatPrice();
    }

    public String getDescription() {
        return mProduct.getDescription();
    }

    public String getStatus() {
        return mProduct.getStatus();
    }
}
