package com.framgia.forder.screen.shopinfo;

import android.databinding.BaseObservable;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopInfo;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.managerdetail.ManagerDetailFragment;
import com.framgia.forder.screen.orderhistoryshop.OrderHistoryShopFragment;
import com.framgia.forder.screen.ordershop.OrderShopFragment;
import com.framgia.forder.screen.shopupdate.ShopUpdateFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the Shopinfo screen.
 */

public class ShopinfoViewModel extends BaseObservable implements ShopinfoContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User> {

    private static final String TAG = "ShopinfoFragment";
    private final Navigator mNavigator;
    private ShopinfoContract.Presenter mPresenter;
    private final ShopManagement mShopManagement;
    private final ShopInfo mShopInfo;
    private final ManagerShopInfoAdapter mAdapter;

    ShopinfoViewModel(Navigator navigator, ShopManagement shopManagement,
            ManagerShopInfoAdapter adapter) {
        mNavigator = navigator;
        mShopManagement = shopManagement;
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
        mShopInfo = mShopManagement.getShopInfos().get(1);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ShopinfoContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.getListManagerOfShop(mShopManagement.getShop().getId());
    }

    public String getShopImage() {
        if (mShopManagement.getShop() != null
                && mShopManagement.getShop().getCoverImage() != null
                && mShopManagement.getShop().getCoverImage().getImage() != null) {
            return mShopManagement.getShop().getCoverImage().getImage().getUrl();
        }
        return "";
    }

    public String getShopName() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getName();
        }
        return "";
    }

    public float getRating() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getAverageRating();
        }
        return 0;
    }

    public String getNumberProduct() {
        if (mShopInfo != null) {
            return String.valueOf(mShopInfo.getNumberProduct());
        }
        return "";
    }

    public String getTimeOpenShop() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getTimeOpenShop();
        }
        return "";
    }

    public String getShopAvatar() {
        if (mShopManagement.getShop() != null
                && mShopManagement.getShop().getCollectionAvatar() != null
                && mShopManagement.getShop().getCollectionAvatar().getImage() != null) {
            return mShopManagement.getShop().getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public void onClickEditShop() {
        mNavigator.goNextChildFragment(R.id.layout_content,
                ShopUpdateFragment.newInstance(mShopManagement), true, Navigator.BOTTOM_UP, TAG);
    }

    @Override
    public void onGetListManagerOfShopSuccess(List<User> users) {
        mAdapter.updateData(users);
    }

    @Override
    public void onGetListManagerOfShopError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    public ManagerShopInfoAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onItemRecyclerViewClick(User user) {
        mNavigator.goNextChildFragment(R.id.layout_content, ManagerDetailFragment.newInstance(user),
                true, Navigator.BOTTOM_UP, TAG);
    }

    public void onClickListOrderShop() {
        mNavigator.goNextChildFragment(R.id.layout_content,
                OrderShopFragment.newInstance(this.mShopManagement), true, Navigator.BOTTOM_UP,
                TAG);
    }

    public void onClickOrderHistory() {
        mNavigator.goNextChildFragment(R.id.layout_content, OrderHistoryShopFragment.newInstance(),
                true,
                Navigator.BOTTOM_UP, TAG);
    }
}
