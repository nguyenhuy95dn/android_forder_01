package com.framgia.forder.data.source;

import com.framgia.forder.data.source.local.realm.BaseLocalDataSource;
import android.support.annotation.NonNull;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShoppingCart;
import java.util.List;
import rx.Observable;

public interface ProductDataSource {
    interface LocalDataSource extends BaseLocalDataSource {

        Observable<Void> addShoppingCard(@NonNull Product product);

        Observable<List<ShoppingCart>> getAllShoppingCart();
    }
    interface RemoteDataSource {
        Observable<List<Product>> getListProduct(int domainId);
    }
}
