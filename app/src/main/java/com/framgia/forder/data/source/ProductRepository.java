package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShoppingCart;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import java.util.List;
import rx.Observable;

/**
 * Created by tri on 17/04/2017.
 */

public class ProductRepository {
    private ProductDataSource.LocalDataSource mLocalDataSource;

    public ProductRepository(ProductLocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    public ProductDataSource.LocalDataSource getLocalDataSource() {
        return mLocalDataSource;
    }

    public Observable<Void> addToCart(Product product) {
        return mLocalDataSource.addShoppingCard(product);
    }

    public Observable<List<ShoppingCart>> getAllShoppingCart() {
        return mLocalDataSource.getAllShoppingCart();
    }

    public void openTransaction() {
        mLocalDataSource.openTransaction();
    }

    public void closeTransaction() {
        mLocalDataSource.closeTransaction();
    }
}
