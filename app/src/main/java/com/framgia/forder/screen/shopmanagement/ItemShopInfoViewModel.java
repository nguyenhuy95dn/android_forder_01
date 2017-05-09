package com.framgia.forder.screen.shopmanagement;

import android.content.Context;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopInfo;

/**
 * Created by levutantuan on 5/8/17.
 */

public class ItemShopInfoViewModel {
    private final Context mContext;
    private final ShopInfo mShopInfo;

    public ItemShopInfoViewModel(Context context, ShopInfo shopInfo) {
        this.mContext = context;
        mShopInfo = shopInfo;
    }

    public String getDomainId() {
        if (mShopInfo != null) {
            return String.valueOf(mShopInfo.getDomainId());
        }
        return "";
    }

    public String getDomainName() {
        if (mShopInfo != null) {
            return mShopInfo.getDomainName();
        }
        return "";
    }

    public String getNumberUser() {
        if (mShopInfo != null) {
            return mContext.getString(R.string.user_with_count, mShopInfo.getNumberUser());
        }
        return mContext.getString(R.string.user_with_count, 0);
    }

    public String getNumberShop() {
        if (mShopInfo != null) {
            return mContext.getString(R.string.shop_with_count,
                    Integer.parseInt(String.valueOf(mShopInfo.getNumberShop())));
        }
        return mContext.getString(R.string.shop_with_count, 0);
    }

    public String getNumberProduct() {
        if (mShopInfo != null) {
            return mContext.getString(R.string.product_with_count, mShopInfo.getNumberProduct());
        }
        return mContext.getString(R.string.product_with_count, 0);
    }

    public String getStatus() {
        if (mShopInfo != null && mShopInfo.getDomain() != null) {
            return mContext.getString(R.string.status_domain, mShopInfo.getDomain().getStatus());
        }
        return mContext.getString(R.string.status_domain, "");
    }
}
