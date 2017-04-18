package com.framgia.forder.data.source;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShoppingCart;
import com.framgia.forder.data.source.local.realm.BaseLocalDataSource;
import java.util.List;
import rx.Observable;

public interface ProductDataSource {
    interface LocalDataSource extends BaseLocalDataSource {

        Observable<Void> addShoppingCard(@NonNull Product product);

        Observable<Void> deleteShoppingCard(@NonNull int productId, @NonNull int domainId);

        Observable<Void> upQuantity(@NonNull int productId, @NonNull int domainId);

        Observable<Void> downQuantity(@NonNull int productId, @NonNull int domainId);

        Observable<Integer> getNumberItem(@NonNull int domainId);

        Observable<Double> getTotalPrice(@NonNull int domainId);

        Observable<List<ShoppingCart>> getAllShoppingCart();
    }

    interface RemoteDataSource {
        Observable<List<Product>> getListProduct(int domainId);
    }
}
