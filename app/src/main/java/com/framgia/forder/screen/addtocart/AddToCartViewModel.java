package com.framgia.forder.screen.addtocart;

import android.annotation.SuppressLint;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.main.LoadCartListener;
import com.framgia.forder.screen.quickorder.DismissDialogListener;
import com.framgia.forder.utils.Utils;

import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Exposes the data to be used in the Add_to_cart screen.
 */

public class AddToCartViewModel extends BaseObservable implements AddToCartContract.ViewModel {

    private static final String TAG = "AddToCartViewModel";
    private static final int DEFAULT_PRODUCT_NUMBER = 1;

    private AddToCartContract.Presenter mPresenter;
    private final Product mProduct;
    private final double mPrice;
    private final int mTotalProductInCart;
    private final LoadCartListener mLoadCartListener;
    private int mQuantity;
    private final DismissDialogListener mDismissDialogListener;
    private double mTotalPriceInCart;

    AddToCartViewModel(Product product, int totalProductInCart, int quantity,
            DismissDialogListener dismissDialogListener, LoadCartListener loadCartListener) {
        mProduct = product;
        mTotalProductInCart = totalProductInCart;
        mDismissDialogListener = dismissDialogListener;
        mLoadCartListener = loadCartListener;
        mQuantity = quantity;
        mPrice = product.getPrice();
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getTotalPrice();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(AddToCartContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public String getProductImage() {
        if (mProduct.getCollectionImage() != null
                && mProduct.getCollectionImage().getImage() != null) {
            return mProduct.getCollectionImage().getImage().getUrl();
        }
        return "";
    }

    public String getProductName() {
        return mProduct.getName();
    }

    @Bindable
    public String getQuantity() {
        return String.valueOf(mQuantity);
    }

    @SuppressLint("DefaultLocale")
    public String getProductPrice() {
        return String.format(Utils.FORMAT_PRICE, mPrice) + UNIT_MONEY;
    }

    public String getTotalProductInCart() {
        return String.valueOf(mTotalProductInCart);
    }

    @SuppressLint("DefaultLocale")
    @Bindable
    public String getToTalPriceInCart() {
        return String.format(Utils.FORMAT_PRICE, mTotalPriceInCart) + UNIT_MONEY;
    }

    public void onClickContinueShopping() {
        mDismissDialogListener.onDialogDismiss();
    }

    public void onClickUpQuantity() {
        mQuantity++;
        notifyPropertyChanged(BR.quantity);
        mPresenter.addToCart(mProduct);
    }

    public void onClickDownQuantity() {
        if (mQuantity == DEFAULT_PRODUCT_NUMBER) {
            return;
        }
        mQuantity--;
        notifyPropertyChanged(BR.quantity);
        mPresenter.downQuantity(mProduct);
    }

    public void onClickRemoveProductInCart() {
        mPresenter.deleteProduct(mProduct);
        mDismissDialogListener.onDialogDismiss();
    }

    @Override
    public void onGetTotalPriceSuccess(Double totalPrice) {
        mTotalPriceInCart = totalPrice;
        notifyPropertyChanged(BR.toTalPriceInCart);
    }

    @Override
    public void onGetTotalPriceError(BaseException error) {
        Log.e(TAG, "onGetTotalPriceError: ", error);
    }

    @Override
    public void onAddToCartSuccess() {
        mPresenter.getTotalPrice();
    }

    @Override
    public void onAddToCartError(Throwable e) {
        Log.e(TAG, "onAddToCartError: ", e);
    }

    @Override
    public void onDeleteProductInCartSuccess() {
        mLoadCartListener.onReloadCart();
    }

    @Override
    public void onDeleteProductInCartError(Throwable e) {
        Log.e(TAG, "onDeleteProductInCartError: ", e);
    }

    @Override
    public void onDownQuantitySuccess() {
        mPresenter.getTotalPrice();
    }

    @Override
    public void onDownQuantityError(Throwable e) {
        Log.e(TAG, "onDownQuantityError: ", e);
    }
}
