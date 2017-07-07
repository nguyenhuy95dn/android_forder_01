package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by levutantuan on 5/3/17.
 */

public class ItemShopManagementViewModel {

    private final ShopManagement mShopManagement;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mListener;
    private final boolean mStatusShop;
    private final ListShopManagementAdapter.ChangeStatusShopManagement mChangeStatusShopManagement;

    ItemShopManagementViewModel(ShopManagement shopManagements,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener,
            ListShopManagementAdapter.ChangeStatusShopManagement changeStatusShopManagement) {
        mShopManagement = shopManagements;
        mStatusShop = mShopManagement.getShop().isFormatStatus();
        mListener = listener;
        mChangeStatusShopManagement = changeStatusShopManagement;
    }

    public String getShopImage() {
        if (mShopManagement.getShop() != null
                && mShopManagement.getShop().getCollectionAvatar() != null
                && mShopManagement.getShop().getCollectionAvatar().getImage() != null) {
            return mShopManagement.getShop().getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getShopName() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getName();
        }
        return "";
    }

    public String getDescription() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getDescription();
        }
        return "";
    }

    public void onItemClicked() {
        if (mListener == null) {
            return;
        }
        mListener.onItemRecyclerViewClick(mShopManagement);
    }

    public boolean isStatusShop() {
        return mStatusShop;
    }

    public void checked() {
        //Todo edit later
        //        if (mStatusShop) {
        //            mChangeStatusShopManagement.onChangeStatusShop(mShopManagement.getShop()
        // .getId(),
        //                    STATUS_OFF);
        //        } else {
        //            mChangeStatusShopManagement.onChangeStatusShop(mShopManagement.getShop()
        // .getId(),
        //                    STATUS_ON);
        //        }
    }
}
