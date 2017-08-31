package com.framgia.forder.screen.addtocart;

import android.annotation.SuppressLint;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.screen.quickorder.DismissDialogListener;
import com.framgia.forder.utils.Utils;

import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Exposes the data to be used in the Add_to_cart screen.
 */

public class AddToCartViewModel implements AddToCartContract.ViewModel {

    private AddToCartContract.Presenter mPresenter;
    private final Product mProduct;
    private final double mPrice;
    private final int mQuantity;
    private final int mProductInCart;
    private final double mTotalPriceInCart;
    private final DismissDialogListener mDismissDialogListener;

    AddToCartViewModel(Product product, int productInCart, double totalPriceInCart,
            DismissDialogListener dismissDialogListener) {
        mProduct = product;
        mProductInCart = productInCart;
        mTotalPriceInCart = totalPriceInCart;
        mDismissDialogListener = dismissDialogListener;
        mQuantity = 1;
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

    public String getQuantity() {
        return String.valueOf(mQuantity);
    }

    @SuppressLint("DefaultLocale")
    public String getProductPrice() {
        return String.format(Utils.FORMAT_PRICE, mPrice * mQuantity) + UNIT_MONEY;
    }

    public String getProductInCart() {
        return String.valueOf(mProductInCart);
    }

    @SuppressLint("DefaultLocale")
    public String getToTalPriceInCart() {
        return String.format(Utils.FORMAT_PRICE, mTotalPriceInCart) + UNIT_MONEY;
    }

    public void onClickContinueShopping() {
    }

    public void onClickUpQuantity() {
    }

    public void onClickDownQuantity() {
    }

    public void onClickRemoveProductInCart() {
        mDismissDialogListener.onDialogDismiss();
    }
}
