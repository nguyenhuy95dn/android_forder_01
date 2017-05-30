package com.framgia.forder.data.source.remote.api.request;

import com.framgia.forder.data.model.RegisterProductInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;

/**
 * Created by Age on 5/29/2017.
 */

public class UpdateProductRequest {
    @Expose
    @SerializedName("product")
    private RegisterProductInfo mProduct;
    private InputStream mImage;
    private int mProductId;

    public RegisterProductInfo getProduct() {
        return mProduct;
    }

    public void setProduct(RegisterProductInfo product) {
        mProduct = product;
    }

    public InputStream getImage() {
        return mImage;
    }

    public void setImage(InputStream image) {
        mImage = image;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }
}
