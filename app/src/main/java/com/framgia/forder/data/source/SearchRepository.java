package com.framgia.forder.data.source;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.DataSuggest;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.local.SearchLocalDataSource;
import com.framgia.forder.data.source.remote.SearchRemoteDataSource;
import com.framgia.forder.data.source.remote.api.response.SearchResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 4/20/2017.
 */

public class SearchRepository {
    private SearchDataSource.RemoteDataSource mRemoteDataSource;
    private SearchDataSource.LocalDataSource mLocalSearchDataSource;
    private DomainRepository mDomainRepository;
    private int mCurrentDomainId;

    public SearchRepository(SearchRemoteDataSource remoteDataSource,
            SearchLocalDataSource productLocalDataSource, DomainRepository domainRepository) {
        mRemoteDataSource = remoteDataSource;
        mLocalSearchDataSource = productLocalDataSource;
        mDomainRepository = domainRepository;
        mCurrentDomainId = mDomainRepository.getCurrentDomain().getId();
    }

    public Observable<SearchResponse> search(int doMain, String keyWord) {
        return mRemoteDataSource.search(doMain, keyWord);
    }

    public Observable<Void> addProductDataSuggest(@NonNull List<Product> products) {
        return mLocalSearchDataSource.addProductDataSuggest(products, mCurrentDomainId);
    }

    public Observable<List<DataSuggest>> getAllDataSuggest() {
        return mLocalSearchDataSource.getAllName(mCurrentDomainId);
    }

    public Observable<Void> addShopDataSuggest(@NonNull List<Shop> shops) {
        return mLocalSearchDataSource.addShopDataSuggest(shops, mCurrentDomainId);
    }
}
