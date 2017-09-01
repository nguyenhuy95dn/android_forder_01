package com.framgia.forder.screen.mainpage.ordercart;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.model.Product;
import java.util.List;

/**
 * Created by nguyenhuy95dn on 9/1/2017.
 */

public abstract class BaseOrderCartViewModel extends BaseObservable {

    private static final int DEFAULT_QUANTITY = 1;

    public int getTotalProductInCart(List<Cart> carts) {
        int productNumber = 0;
        for (Cart cart : carts) {
            productNumber += cart.getCartItemList().size();
        }
        return productNumber;
    }

    public int getQuantityProduct(List<Cart> carts, Product product) {
        for (Cart cart : carts) {
            for (CartItem cartItem : cart.getCartItemList()) {
                if (cartItem.getProductId() == product.getId()) {
                    return cartItem.getQuantity();
                }
            }
        }
        return DEFAULT_QUANTITY;
    }
}
