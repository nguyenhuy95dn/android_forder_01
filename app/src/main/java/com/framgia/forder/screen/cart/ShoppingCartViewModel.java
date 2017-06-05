package com.framgia.forder.screen.cart;

import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Exposes the data to be used in the ShoppingCart screen.
 */

public class ShoppingCartViewModel extends BaseObservable
        implements ShoppingCartContract.ViewModel, OrderItemListener {
    private static final String TAG = "ShoppingCartFragment";
    private final ShoppingCartAdapter mShoppingCartAdapter;
    private ShoppingCartContract.Presenter mPresenter;
    private double mTotalPrice;
    private List<Cart> mCartList;
    private final DialogManager mDialogManager;
    private final Navigator mNavigator;
    private Cart mCart;

    ShoppingCartViewModel(ShoppingCartAdapter shoppingCartAdapter, DialogManager dialogManager,
            Navigator navigator) {
        mShoppingCartAdapter = shoppingCartAdapter;
        shoppingCartAdapter.setOrderItemListener(this);
        mDialogManager = dialogManager;
        mNavigator = navigator;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListCart();
        mPresenter.getTotalPrice();
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
        mCartList = cartList;
    }

    @Override
    public void onOrderOneShop(final Cart cart) {
        if (cart == null) {
            return;
        }
        mCart = cart;
        List<Cart> carts = new ArrayList<>();
        carts.add(cart);
        final OrderRequest request = new OrderRequest();
        request.setCartList(carts);
        mDialogManager.dialogwithNoTitleTwoButton(R.string.mgs_this_order,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.orderOneShop(request);
                    }
                });
        mDialogManager.show();
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
    public void onDeleteProduct(final CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        mDialogManager.dialogwithNoTitleTwoButton(R.string.mgs_delete_product,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteProduct(cartItem);
                    }
                });
        mDialogManager.show();
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
        reloadData();
    }

    @Override
    public void onDownQuantityError(Throwable throwable) {
        Log.e(TAG, "onDownQuantityError", throwable);
    }

    @Override
    public void onDownQuantitySuccess() {
        reloadData();
    }

    @Override
    public void onDeleteProductError(Throwable throwable) {
        Log.e(TAG, "onDeleteProductError", throwable);
    }

    @Override
    public void onDeleteProductSuccess() {
        reloadData();
    }

    @Override
    public void reloadData() {
        mPresenter.getListCart();
        mPresenter.getTotalPrice();
    }

    @Override
    public void onGetTotalPriceSuccess(double totalPrice) {
        mTotalPrice = totalPrice;
        notifyPropertyChanged(BR.totalPrice);
        mPresenter.getListCart();
    }

    @Override
    public void onGetTotalPriceError(Throwable throwable) {
        Log.e(TAG, "onGetTotalPriceError", throwable);
    }

    @Override
    public void onOrderAllShop() {
        final OrderRequest request = new OrderRequest();
        request.setCartList(mCartList);
        mDialogManager.dialogwithNoTitleTwoButton(R.string.mgs_order_all,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.orderAllShop(request);
                    }
                });
        mDialogManager.show();
    }

    @Override
    public void onOrderAllShopError(Throwable throwable) {
        Log.e(TAG, "onOrderAllShopError", throwable);
    }

    @Override
    public void onOrderAllShopSuccess() {
        mNavigator.showToast(R.string.order_successful);
        mPresenter.removeAllShop();
        reloadData();
    }

    @Override
    public void onOrderOneShopError(Throwable throwable) {
        Log.e(TAG, "onOrderOneShopError", throwable);
    }

    @Override
    public void onOrderOneShopSuccess() {
        mNavigator.showToast(R.string.order_successful);
        mPresenter.removeOneShop(mCart);
        reloadData();
    }

    @Override
    public void onRemoveShopSuccessful() {
        reloadData();
    }

    @Override
    public void onRemoveShopError(Throwable e) {
        Log.e(TAG, "onRemoveShopError: ", e);
    }

    @Override
    public void onClickIconWarning() {
        mDialogManager.dialogWarning((R.string.mgs_product_time_out));
        mDialogManager.show();
    }

    @Bindable
    public String getTotalPrice() {
        return String.format(FORMAT_PRICE, mTotalPrice) + UNIT_MONEY;
    }

    public Cart getCart() {
        return mCart;
    }

    public void setCart(Cart cart) {
        mCart = cart;
    }
}
