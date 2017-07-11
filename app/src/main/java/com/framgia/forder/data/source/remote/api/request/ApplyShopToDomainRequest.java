package com.framgia.forder.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 5/11/17.
 */

public class ApplyShopToDomainRequest extends BaseRequest {

    @Expose
    @SerializedName("domain_id")
    private int mDomainId;
    @Expose
    @SerializedName("shop_id")
    private int mShopId;

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }
}
