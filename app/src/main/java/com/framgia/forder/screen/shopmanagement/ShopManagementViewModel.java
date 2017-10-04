package com.framgia.forder.screen.shopmanagement;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.createshop.CreateshopFragment;
import com.framgia.forder.screen.shopinfo.ShopInformationPageContainerFragment;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;

/**
 * Exposes the data to be used in the ShopManagement screen.
 */

public class ShopManagementViewModel extends BaseObservable
        implements ShopManagementContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>,
        ListShopManagementAdapter.ChangeStatusShopManagement {

    private static final String TAG = "ShopDetailFragment";

    private final Navigator mNavigator;
    private final ListShopManagementAdapter mListShopManagementAdapter;
    private ShopManagementContract.Presenter mPresenter;
    private boolean mIsProgressbarVisibleShopManagement;
    private final DialogManager mDialogManager;
    private boolean mIsHaveData;

    ShopManagementViewModel(Navigator navigator, ListShopManagementAdapter adapter,
            DialogManager dialogManager) {
        mNavigator = navigator;
        mListShopManagementAdapter = adapter;
        mDialogManager = dialogManager;
        mListShopManagementAdapter.setItemClickListener(this);
        mListShopManagementAdapter.setChangeStatusShopManagement(this);
        setProgressbarVisibleShopManagement(false);
        setHaveData(true);
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
    public void setPresenter(ShopManagementContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onClickShopManagement() {
        mNavigator.goNextChildFragment(R.id.layout_content, CreateshopFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, TAG);
    }

    @Override
    public void onShowMessageError(BaseException exception) {
        Log.e(TAG, "onShowMessageError: ", exception);
        setHaveData(false);
    }

    @Override
    public void onGetListShopManagementSuccess(List<ShopManagement> shopManagements) {
        if (shopManagements.size() == 0) {
            setHaveData(false);
            return;
        }
        mListShopManagementAdapter.updateData(shopManagements);
        setHaveData(true);
    }

    @Override
    public void onShowProgressBar() {
        setProgressbarVisibleShopManagement(true);
    }

    @Override
    public void onHideProgressBar() {
        setProgressbarVisibleShopManagement(false);
    }

    @Override
    public void onShowProgressDialog() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressDialog() {
        mDialogManager.dismissProgressDialog();
    }

    @Override
    public void onChangeStatusShopSuccess() {
    }

    @Override
    public void onReLoadData() {
        mPresenter.getListShopManagement();
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (!(item instanceof ShopManagement)) {
            return;
        }
        ShopManagement shopManagement = (ShopManagement) item;
        mNavigator.goNextChildFragment(R.id.layout_content,
                ShopInformationPageContainerFragment.newInstance(shopManagement), true,
                Navigator.RIGHT_LEFT, TAG);
    }

    public ListShopManagementAdapter getListShopManagementAdapter() {
        return mListShopManagementAdapter;
    }

    @Bindable
    public boolean isProgressbarVisibleShopManagement() {
        return mIsProgressbarVisibleShopManagement;
    }

    private void setProgressbarVisibleShopManagement(boolean progressbarVisibleShopManagement) {
        mIsProgressbarVisibleShopManagement = progressbarVisibleShopManagement;
        notifyPropertyChanged(BR.progressbarVisibleShopManagement);
    }

    @Override
    public void onChangeStatusShop(int shopId, String status) {
        mPresenter.requestChangeStatusShop(shopId, status);
    }

    @Bindable
    public boolean isHaveData() {
        return mIsHaveData;
    }

    public void setHaveData(boolean haveData) {
        mIsHaveData = haveData;
        notifyPropertyChanged(BR.haveData);
    }
}
