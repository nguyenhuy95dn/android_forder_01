package com.framgia.forder.screen.shopinfo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.response.DomainToRequestShopResponse;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.addmanagershop.AddManagerShopFragment;
import com.framgia.forder.screen.managerdetail.ManagerDetailFragment;
import com.framgia.forder.screen.orderhistoryshop.OrderHistoryShopFragment;
import com.framgia.forder.screen.ordershop.OrderShopFragment;
import com.framgia.forder.screen.shopinfo.listdomain.ListDomainAdapter;
import com.framgia.forder.screen.shopupdate.ShopUpdateFragment;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;

import static com.framgia.forder.utils.Constant.DEFAULT_VALUE;

/**
 * Exposes the data to be used in the Shopinfo screen.
 */

public class ShopinfoViewModel extends BaseObservable implements ShopinfoContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User>,
        ListDomainAdapter.ShopDomainManagementListener {

    private static final String TAG = "ShopinfoFragment";

    private final Navigator mNavigator;
    private ShopinfoContract.Presenter mPresenter;
    private final ShopManagement mShopManagement;
    private final ManagerShopInfoAdapter mAdapter;
    private final ListDomainAdapter mDomainAdapter;
    private final DialogManager mDialogManager;
    private boolean mIsProgressBarListDomain;
    private boolean mIsProgressBarListManager;

    ShopinfoViewModel(Navigator navigator, ShopManagement shopManagement,
            ManagerShopInfoAdapter adapter, ListDomainAdapter domainAdapter,
            DialogManager dialogManager) {
        mNavigator = navigator;
        mShopManagement = shopManagement;
        mAdapter = adapter;
        mDomainAdapter = domainAdapter;
        mDialogManager = dialogManager;
        mAdapter.setItemClickListener(this);
        mDomainAdapter.setDomainManagementListener(this);
        setProgressBarListDomain(false);
        setProgressBarListManager(false);
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
        mPresenter.getListDomainToRequestShop(mShopManagement.getShop().getId());
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

    public String getRating() {
        if (mShopManagement.getShop() != null) {
            return String.valueOf(mShopManagement.getShop().getAverageRating());
        }
        return String.valueOf(DEFAULT_VALUE);
    }

    public String getTimeReject() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getTimeAutoReject();
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

    @Override
    public void onApplyToDomainSuccess() {
        mNavigator.showToastCustomActivity(R.string.request_success);
        mPresenter.getListDomainToRequestShop(mShopManagement.getShop().getId());
    }

    @Override
    public void onLeaveToDomainSuccess() {
        mNavigator.showToastCustomActivity(R.string.cancel_success);
        mPresenter.getListDomainToRequestShop(mShopManagement.getShop().getId());
    }

    @Override
    public void onApplyOrLeaveToDomainError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onShowProgressBarDialog() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBarDialog() {
        mDialogManager.dismissProgressDialog();
    }

    @Override
    public void onGetListDomainSuccess(List<DomainToRequestShopResponse.DomainToRequest> domains) {
        mDomainAdapter.updateData(domains);
    }

    @Override
    public void onShowProgressBarListManager() {
        setProgressBarListManager(true);
    }

    @Override
    public void onHideProgressBarListManager() {
        setProgressBarListManager(false);
    }

    @Override
    public void onShowProgressBarListDomain() {
        setProgressBarListDomain(true);
    }

    @Override
    public void onHideProgressBarListDomain() {
        setProgressBarListDomain(false);
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
        mNavigator.goNextChildFragment(R.id.layout_content,
                OrderHistoryShopFragment.newInstance(this.mShopManagement), true,
                Navigator.BOTTOM_UP, TAG);
    }

    public void onClickShowListUser() {
        mNavigator.goNextChildFragment(R.id.layout_content, AddManagerShopFragment.newInstance(),
                true, Navigator.BOTTOM_UP, TAG);
    }

    public ManagerShopInfoAdapter getAdapter() {
        return mAdapter;
    }

    public ListDomainAdapter getDomainAdapter() {
        return mDomainAdapter;
    }

    @Override
    public void onApplyToDomain(ApplyShopToDomainRequest applyShopToDomainRequest) {
        applyShopToDomainRequest.setShopId(mShopManagement.getShop().getId());
        mPresenter.onApplyToDomain(applyShopToDomainRequest);
    }

    @Override
    public void onLeaveToDomain(int domainId) {
        mPresenter.onLeaveToDomain(domainId, mShopManagement.getShop().getId(), true);
    }

    @Bindable
    public boolean isProgressBarListDomain() {
        return mIsProgressBarListDomain;
    }

    private void setProgressBarListDomain(boolean progressBarListDomain) {
        mIsProgressBarListDomain = progressBarListDomain;
        notifyPropertyChanged(BR.progressBarListDomain);
    }

    @Bindable
    public boolean isProgressBarListManager() {
        return mIsProgressBarListManager;
    }

    private void setProgressBarListManager(boolean progressBarListManager) {
        mIsProgressBarListManager = progressBarListManager;
        notifyPropertyChanged(BR.progressBarListManager);
    }
}
