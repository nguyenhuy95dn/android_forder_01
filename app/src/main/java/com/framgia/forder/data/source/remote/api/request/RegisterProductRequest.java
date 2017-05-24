package com.framgia.forder.data.source.remote.api.request;

import com.framgia.forder.data.model.RegisterProductInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;

/**
 * Created by Age on 5/23/2017.
 */

public class RegisterProductRequest {
    @Expose
    @SerializedName("user_email")
    private String mUserEmail;
    @Expose
    @SerializedName("user_token")
    private String mUserToken;
    @Expose
    @SerializedName("product")
    private RegisterProductInfo mProduct;
    private InputStream mImage;

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }

    public String getUserToken() {
        return mUserToken;
    }

    public void setUserToken(String userToken) {
        mUserToken = userToken;
    }

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
}
