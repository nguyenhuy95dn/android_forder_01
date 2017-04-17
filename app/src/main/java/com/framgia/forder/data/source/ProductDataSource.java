package com.framgia.forder.data.source;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShoppingCart;
import com.framgia.forder.data.source.local.realm.BaseLocalDataSource;
import java.util.List;
import rx.Observable;

/**
 * Created by tri on 17/04/2017.
 */

public interface ProductDataSource {
    interface LocalDataSource extends BaseLocalDataSource {

        Observable<Void> addShoppingCard(@NonNull Product product);

        Observable<List<ShoppingCart>> getAllShoppingCart();
    }
}
