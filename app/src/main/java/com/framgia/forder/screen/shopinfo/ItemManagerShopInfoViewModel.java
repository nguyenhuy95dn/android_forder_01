package com.framgia.forder.screen.shopinfo;

import android.databinding.BaseObservable;
import com.framgia.forder.data.source.remote.api.response.ManagerResponse;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by levutantuan on 5/18/17.
 */

public class ItemManagerShopInfoViewModel extends BaseObservable {

    private static final String TAG = "ShopinfoFragment";
    private final ManagerResponse.ManagerDetail mManagerDetail;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<ManagerResponse
            .ManagerDetail>
            mItemClickListener;

    ItemManagerShopInfoViewModel(ManagerResponse.ManagerDetail managerDetail,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<ManagerResponse
                    .ManagerDetail> itemClickListener) {
        mManagerDetail = managerDetail;
        mItemClickListener = itemClickListener;
    }

    public String getAvatar() {
        if (mManagerDetail.getUser() != null
                && mManagerDetail.getUser().getAvatar() != null
                && mManagerDetail.getUser().getAvatar().getImage() != null) {
            return mManagerDetail.getUser().getAvatar().getImage().getUrl();
        }
        return "";
    }

    public void onClickManagerDetail() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mManagerDetail);
    }
}
