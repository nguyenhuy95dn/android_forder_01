package com.framgia.forder.screen.shopmanagement;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.createshop.CreateshopFragment;
import com.framgia.forder.screen.shopinfo.ShopInformationPageContainerFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the ShopManagement screen.
 */

public class ShopManagementViewModel extends BaseObservable
        implements ShopManagementContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private static final String TAG = "ShopDetailFragment";

    private final Navigator mNavigator;
    private final ListShopManagementAdapter mListShopManagementAdapter;
    private ShopManagementContract.Presenter mPresenter;
    private boolean mIsProgressbarVisibleShopManagement;

    ShopManagementViewModel(Navigator navigator, ListShopManagementAdapter adapter) {
        mNavigator = navigator;
        mListShopManagementAdapter = adapter;
        mListShopManagementAdapter.setItemClickListener(this);
        setProgressbarVisibleShopManagement(false);
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
    public void onGetListShopManagementError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onGetListShopManagementSuccess(List<ShopManagement> shopManagements) {
        mListShopManagementAdapter.updateData(shopManagements);
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

    public void setProgressbarVisibleShopManagement(boolean progressbarVisibleShopManagement) {
        mIsProgressbarVisibleShopManagement = progressbarVisibleShopManagement;
        notifyPropertyChanged(BR.progressbarVisibleShopManagement);
    }
}
