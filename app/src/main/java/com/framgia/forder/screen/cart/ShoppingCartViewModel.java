package com.framgia.forder.screen.cart;

import android.util.Log;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import java.util.List;

/**
 * Exposes the data to be used in the ShoppingCart screen.
 */

public class ShoppingCartViewModel implements ShoppingCartContract.ViewModel, OrderItemListener {
    private ShoppingCartAdapter mShoppingCartAdapter;
    private ShoppingCartContract.Presenter mPresenter;
    private static final String TAG = "ShoppingCartFragment";

    public ShoppingCartViewModel(ShoppingCartAdapter shoppingCartAdapter) {
        mShoppingCartAdapter = shoppingCartAdapter;
        shoppingCartAdapter.setOrderItemListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListCart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ShoppingCartContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListCartSuccess(List<Cart> cartList) {
        mShoppingCartAdapter.updateData(cartList);
    }

    @Override
    public void onOrderItem(Cart cart) {
        if (cart == null) {
            return;
        }
        mPresenter.orderItem(cart);
    }

    @Override
    public void onUpQuantity(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        mPresenter.upQuantity(cartItem);
    }

    @Override
    public void onDownQuantity(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        mPresenter.downQuantity(cartItem);
    }

    @Override
    public void onDeleteProduct(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        mPresenter.deleteProduct(cartItem);
    }

    public ShoppingCartAdapter getShoppingCartAdapter() {
        return mShoppingCartAdapter;
    }

    @Override
    public void onGetListCartError(BaseException exception) {
        Log.e(TAG, "onGetListCartError", exception);
    }

    @Override
    public void onUpQuantityError(Throwable throwable) {
        Log.e(TAG, "onUpQuantityError", throwable);
    }

    @Override
    public void onUpQuantitySuccess() {
        mPresenter.getListCart();
    }

    @Override
    public void onDownQuantityError(Throwable throwable) {
        Log.e(TAG, "onDownQuantityError", throwable);
    }

    @Override
    public void onDownQuantitySuccess() {
        mPresenter.getListCart();
    }

    @Override
    public void onDeleteProductError(Throwable throwable) {
        Log.e(TAG, "onDeleteProductError", throwable);
    }

    @Override
    public void onDeleteProductSuccess() {
        mPresenter.getListCart();
    }

    @Override
    public void reloadData() {
        mPresenter.getListCart();
    }
}
