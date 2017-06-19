package com.framgia.forder.screen.quickorder;

import android.annotation.SuppressLint;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.utils.Utils;

import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Exposes the data to be used in the Quickorder screen.
 */

public class QuickOrderViewModel extends BaseObservable implements QuickOrderContract.ViewModel {
    private static final int DEFAULT_PRODUCT_NUMBER = 1;

    private QuickOrderContract.Presenter mPresenter;
    private final Product mProduct;
    private int mQuantity;
    private final double mPrice;
    private String mNote;

    QuickOrderViewModel(Product product) {
        mProduct = product;
        mQuantity = DEFAULT_PRODUCT_NUMBER;
        mPrice = product.getPrice();
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
    public void setPresenter(QuickOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public String getProductImage() {
        if (mProduct != null
                && mProduct.getCollectionImage() != null
                && mProduct.getCollectionImage().getImage() != null) {
            return mProduct.getCollectionImage().getImage().getUrl();
        }
        return "";
    }

    public String getOrderTime() {
        return mProduct.getFormatStartHour() + "-" + mProduct.getFormatEndHour();
    }

    public String getProductName() {
        return mProduct.getName();
    }

    @SuppressLint("DefaultLocale")
    @Bindable
    public String getProductPrice() {
        return String.format(Utils.FORMAT_PRICE, mPrice * mQuantity) + UNIT_MONEY;
    }

    @Bindable
    public String getProductNumber() {
        return String.valueOf(mQuantity);
    }

    public void downQuantity() {
        if (mQuantity == DEFAULT_PRODUCT_NUMBER) {
            return;
        }
        mQuantity--;
        notifyPropertyChanged(BR.productNumber);
        notifyPropertyChanged(BR.productPrice);
    }

    public void upQuantity() {
        mQuantity++;
        notifyPropertyChanged(BR.productNumber);
        notifyPropertyChanged(BR.productPrice);
    }

    @Bindable
    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
        notifyPropertyChanged(BR.note);
    }
}
