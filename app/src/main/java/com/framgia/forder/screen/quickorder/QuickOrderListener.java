package com.framgia.forder.screen.quickorder;

import com.framgia.forder.data.model.Product;

/**
 * Created by Age on 6/19/2017.
 */

public interface QuickOrderListener {
    void onRequestOrderNow(Product product, double totalPrice, int quantity, String note);
}
