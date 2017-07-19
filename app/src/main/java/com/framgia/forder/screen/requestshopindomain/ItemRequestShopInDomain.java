package com.framgia.forder.screen.requestshopindomain;

import com.framgia.forder.data.source.remote.api.response.ShopRequestResponse;

/**
 * Created by nguyenhuy95dn on 7/18/2017.
 */

public class ItemRequestShopInDomain {

    private ShopRequestResponse.ShopContain mShopContain;
    private RequestShopInDomainAdapter.RequestShopInDomainListener mListener;

    ItemRequestShopInDomain(ShopRequestResponse.ShopContain shopContain,
            RequestShopInDomainAdapter.RequestShopInDomainListener listener) {
        mShopContain = shopContain;
        mListener = listener;
    }

    public String getName() {
        if (mShopContain.getShop() != null) {
            return mShopContain.getShop().getName();
        }
        return "";
    }

    public String getDescription() {
        if (mShopContain.getShop() != null) {
            return mShopContain.getShop().getDescription();
        }
        return "";
    }

    public String getNumberProduct() {
        if (mShopContain.getShop() != null) {
            return String.valueOf(mShopContain.getShop().getProducts());
        }
        return "";
    }

    public String getShopImage() {
        if (mShopContain.getShop() != null
                && mShopContain.getShop().getCollectionAvatar() != null
                && mShopContain.getShop().getCollectionAvatar().getImage() != null) {
            return mShopContain.getShop().getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public void onClickAcceptShop() {
        mListener.onClickAcceptShop(mShopContain.getShop());
    }

    public void onClickRejectShop() {
        mListener.onClickRejectShop(mShopContain.getShop());
    }
}
