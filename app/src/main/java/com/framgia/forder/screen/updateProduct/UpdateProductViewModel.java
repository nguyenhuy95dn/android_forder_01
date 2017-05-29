package com.framgia.forder.screen.updateProduct;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.data.model.Product;

/**
 * Exposes the data to be used in the UpdateProduct screen.
 */

public class UpdateProductViewModel extends BaseObservable
        implements UpdateProductContract.ViewModel {

    private UpdateProductContract.Presenter mPresenter;
    private final Context mContext;
    private final Product mProduct;
    private String mImage;
    private String mName;
    private String mDescription;
    private String mPrice;
    private String mStartHour;
    private String mEndHour;

    UpdateProductViewModel(Context context, Product product) {
        mContext = context;
        mProduct = product;
        getDetailProduct(product);
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
    public void setPresenter(UpdateProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    @Bindable
    public String getPrice() {
        return mPrice;
    }

    @Bindable
    public String getStartHour() {
        return mStartHour;
    }

    @Bindable
    public String getEndHour() {
        return mEndHour;
    }

    @Bindable
    public String getImage() {
        return mImage == null ? "" : mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public void setStartHour(String startHour) {
        mStartHour = startHour;
    }

    public void setEndHour(String endHour) {
        mEndHour = endHour;
    }

    private void getDetailProduct(Product product) {
        if (product.getCollectionImage() != null
                && product.getCollectionImage().getImage() != null
                && product.getCollectionImage().getImage().getUrl() != null) {
            mImage = product.getCollectionImage().getImage().getUrl();
        }
        mName = product.getName();
        mDescription = product.getDescription();
        mPrice = product.getFormatPrice();
        mStartHour = product.getFormatStartHour();
        mEndHour = product.getFormatEndHour();
    }
}
