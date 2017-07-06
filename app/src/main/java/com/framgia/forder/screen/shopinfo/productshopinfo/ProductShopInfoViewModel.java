package com.framgia.forder.screen.shopinfo.productshopinfo;

import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.createProduct.CreateProductFragment;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.screen.updateProduct.UpdateProductFragment;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;

/**
 * Exposes the data to be used in the ProductShopInfo screen.
 */

public class ProductShopInfoViewModel extends BaseObservable
        implements ProductShopInfoContract.ViewModel, UpdateProductListener {

    private final Navigator mNavigator;
    private final ProductShopInformationAdapter mAdapter;
    private final ShopManagement mShopManagement;
    private final DialogManager mDialogManager;
    private boolean mIsProgressBarListProductVisible;
    private ProductShopInfoContract.Presenter mPresenter;

    ProductShopInfoViewModel(Navigator navigator, ProductShopInformationAdapter adapter,
            ShopManagement shopManagement, DialogManager dialogManager) {
        mNavigator = navigator;
        mAdapter = adapter;
        mShopManagement = shopManagement;
        mDialogManager = dialogManager;
        mAdapter.setUpdateProductListener(this);
        setProgressBarListProductVisible(false);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListAllProductShopInformation(mShopManagement.getShop().getId());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ProductShopInfoContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.getListAllProductShopInformation(mShopManagement.getShop().getId());
    }

    @Override
    public void onErrorMessage(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onGetListAllProductShopInformationSuccess(List<Product> products) {
        mAdapter.updateData(products);
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
    public void onDeleteProductSuccess() {
        mNavigator.showToastCustomActivity(R.string.delete_success);
    }

    @Override
    public void onShowProgressBar() {
        setProgressBarListProductVisible(true);
    }

    @Override
    public void onHideProgressBar() {
        setProgressBarListProductVisible(false);
    }

    @Override
    public void onUpdateProduct(Product product) {
        mNavigator.goNextChildFragment(R.id.layout_content,
                UpdateProductFragment.newInstance(product), true, Navigator.RIGHT_LEFT,
                "UpdateProductFragment");
    }

    @Override
    public void onDeleteProduct(final int productId) {
        mDialogManager.dialogwithNoTitleTwoButton(R.string.msg_delete_domain,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteProduct(productId, mShopManagement.getShop().getId());
                    }
                });
        mDialogManager.show();
    }

    @Override
    public void onItemRecyclerViewClick(Product product) {
        mNavigator.goNextChildFragment(R.id.layout_content,
                ProductDetailFragment.newInstance(product), true, Navigator.RIGHT_LEFT,
                "ProductDetailFragment");
    }

    public ProductShopInformationAdapter getAdapter() {
        return mAdapter;
    }

    public void onClickCreateProduct() {
        mNavigator.goNextChildFragment(R.id.layout_content,
                CreateProductFragment.newInstance(mShopManagement), true, Navigator.RIGHT_LEFT,
                "CreateProductFragment");
    }

    @Bindable
    public boolean isProgressBarListProductVisible() {
        return mIsProgressBarListProductVisible;
    }

    public void setProgressBarListProductVisible(boolean progressBarListProductVisible) {
        mIsProgressBarListProductVisible = progressBarListProductVisible;
        notifyPropertyChanged(BR.progressBarListProductVisible);
    }
}
