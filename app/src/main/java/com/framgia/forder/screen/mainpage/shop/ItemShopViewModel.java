package com.framgia.forder.screen.mainpage.shop;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by Duong on 4/13/2017.
 */

public class ItemShopViewModel extends BaseObservable {

    private static final String STATUS_ACTIVE = "active";
    private static final String STATUS_INACTIVE = "inactive";

    private final Shop mShop;
    private int mStatusShop;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemShopViewModel(Shop shop,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
        mShop = shop;
        mItemClickListener = listener;
        initValueImageStatus();
    }

    public String getShopImage() {
        if (mShop.getCollectionAvatar() != null && mShop.getCollectionAvatar().getImage() != null) {
            return mShop.getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getShopName() {
        if (mShop != null) {
            return mShop.getName();
        }
        return "";
    }

    public String getDescription() {
        if (mShop != null) {
            return mShop.getDescription();
        }
        return "";
    }

    public float getRating() {
        return mShop.getAverageRating();
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mShop);
    }

    public String getOwnerName() {
        if (mShop != null) {
            return mShop.getNameOwner();
        }
        return "";
    }

    public String getOwnerEmail() {
        if (mShop != null) {
            return mShop.getEmailOwner();
        }
        return "";
    }

    @Bindable
    public int getStatusShop() {
        return mStatusShop;
    }

    public void setStatusShop(int statusShop) {
        mStatusShop = statusShop;
        notifyPropertyChanged(BR.statusShop);
    }

    private void initValueImageStatus() {
        switch (mShop.getStatus()) {
            case STATUS_ACTIVE:
                setStatusShop(R.drawable.ic_open_status);
                break;
            case STATUS_INACTIVE:
                setStatusShop(R.drawable.ic_close_status);
                break;
            default:
                break;
        }
    }
}
