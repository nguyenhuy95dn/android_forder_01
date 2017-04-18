package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShoppingCart;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import java.util.List;
import rx.Observable;

public class ProductRepository {
    private ProductDataSource.RemoteDataSource mRemoteDataSource;
    private ProductDataSource.LocalDataSource mLocalDataSource;

    public ProductRepository(ProductRemoteDataSource remoteDataSource,
            ProductLocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public Observable<List<Product>> getListProduct(int domainId) {
        return mRemoteDataSource.getListProduct(domainId);
    }

    public Observable<Void> addToCart(Product product) {
        return mLocalDataSource.addShoppingCard(product);
    }

    public Observable<Void> deleteCart(int productId, int domainId) {
        return mLocalDataSource.deleteShoppingCard(productId, domainId);
    }

    public Observable<Void> upQuantity(int productId, int domainId) {
        return mLocalDataSource.upQuantity(productId, domainId);
    }

    public Observable<Void> downQuantity(int productId, int domainId) {
        return mLocalDataSource.downQuantity(productId, domainId);
    }

    public Observable<Integer> getNumberItem(int domainId) {
        return mLocalDataSource.getNumberItem(domainId);
    }

    public Observable<Double> getTotalPrice(int domainId) {
        return mLocalDataSource.getTotalPrice(domainId);
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
