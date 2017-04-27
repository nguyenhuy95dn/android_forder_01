package com.framgia.forder.data.source;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.DataSuggest;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.local.realm.BaseLocalDataSource;
import com.framgia.forder.data.source.remote.api.response.SearchResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 4/20/2017.
 */

public interface SearchDataSource {
    interface LocalDataSource extends BaseLocalDataSource {
        Observable<List<DataSuggest>> getAllName(int domainId);

        Observable<Void> addProductDataSuggest(@NonNull List<Product> products,
                @NonNull int domainId);

        Observable<Void> addShopDataSuggest(@NonNull List<Shop> shops,
                @NonNull int currentDomainId);
    }
    interface RemoteDataSource {
        Observable<SearchResponse> search(int domainId, String keyWord);
    }
}
