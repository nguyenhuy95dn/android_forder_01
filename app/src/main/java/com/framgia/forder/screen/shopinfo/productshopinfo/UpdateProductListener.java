package com.framgia.forder.screen.shopinfo.productshopinfo;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by Age on 5/29/2017.
 */

public interface UpdateProductListener
        extends BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Product> {
    void onUpdateProduct(Product product);
}
