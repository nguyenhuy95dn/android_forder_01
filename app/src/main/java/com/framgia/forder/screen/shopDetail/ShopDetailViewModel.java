package com.framgia.forder.screen.shopDetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listProduct.ListProductFragment;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.screen.productdetail.adapter.ProductShopAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the DetailShop screen.
 */

public class ShopDetailViewModel extends BaseObservable implements ShopDetailContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private static final String TAG = "ProductDetailFragment";

    private ShopDetailContract.Presenter mPresenter;
    private final Shop mShop;
    private final ProductShopAdapter mProductShopAdapter;
    private final Navigator mNavigator;
    private boolean mIsProgressBarListProductVisible;
    private int mButtonFollow;
    private int mTextFollow;
    private boolean mIsFollow;

    ShopDetailViewModel(Shop shop, ProductShopAdapter productShopAdapter, Navigator navigator) {
        mShop = shop;
        mProductShopAdapter = productShopAdapter;
        mNavigator = navigator;
        mProductShopAdapter.setItemClickListener(this);
        setProgressBarListProductVisible(false);
        //Todo remove later when api update we no need this variable
        mIsFollow = true;
        setButtonFollow(R.drawable.border_button_follow);
        setTextFollow(R.string.follow);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListAllProductShop(mShop.getId());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ShopDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getName() {
        return mShop.getName();
    }

    @Bindable
    public String getDescription() {
        return mShop.getDescription();
    }

    @Bindable
    public float getAverageRating() {
        return mShop.getAverageRating();
    }

    @Bindable
    public String getShopAvatar() {
        if (mShop.getCollectionAvatar() != null && mShop.getCollectionAvatar().getImage() != null) {
            return mShop.getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    @Bindable
    public String getShopImage() {
        if (mShop.getCoverImage() != null && mShop.getCoverImage().getImage() != null) {
            return mShop.getCoverImage().getImage().getUrl();
        }
        return "";
    }

    @Bindable
    public String getOwnerAvatar() {
        if (mShop.getOwnerAvatar() != null && mShop.getOwnerAvatar().getImage() != null) {
            return mShop.getOwnerAvatar().getImage().getUrl();
        }
        return "";
    }

    @Bindable
    public String getStatus() {
        return mShop.getStatus();
    }

    @Bindable
    public String getShopOwnerEmail() {
        return mShop.getEmailOwner();
    }

    @Bindable
    public String getShopOwnerName() {
        return mShop.getNameOwner();
    }

    @Override
    public void onGetListAllProductShopSuccess(List<Product> products) {
        mProductShopAdapter.updateData(products);
    }

    @Override
    public void onGetListAllProductShopError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
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
    public void onItemRecyclerViewClick(Object item) {
        if (!(item instanceof Product)) {
            return;
        }
        Product product = (Product) item;
        mNavigator.goNextChildFragment(R.id.layout_content,
                ProductDetailFragment.newInstance(product), true, Navigator.RIGHT_LEFT, TAG);
    }

    @Bindable
    public boolean isProgressBarListProductVisible() {
        return mIsProgressBarListProductVisible;
    }

    private void setProgressBarListProductVisible(boolean progressBarListProductVisible) {
        mIsProgressBarListProductVisible = progressBarListProductVisible;
        notifyPropertyChanged(BR.progressBarListProductVisible);
    }

    public ProductShopAdapter getProductShopAdapter() {
        return mProductShopAdapter;
    }

    public void onClickSeeAllProduct() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListProductFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, TAG);
    }

    @Bindable
    public int getButtonFollow() {
        return mButtonFollow;
    }

    private void setButtonFollow(int buttonFollow) {
        mButtonFollow = buttonFollow;
        notifyPropertyChanged(BR.buttonFollow);
    }

    @Bindable
    public int getTextFollow() {
        return mTextFollow;
    }

    private void setTextFollow(int textFollow) {
        mTextFollow = textFollow;
        notifyPropertyChanged(BR.textFollow);
    }

    public void onClickFollow() {
        //Todo call API in here!
        mIsFollow = !mIsFollow;
        setButtonFollow(
                mIsFollow ? R.drawable.border_button_follow : R.drawable.border_button_unfollow);
        setTextFollow(mIsFollow ? R.string.follow : R.string.unfollow);
    }
}
