package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.source.SearchDataSource;
import com.framgia.forder.data.source.remote.api.response.SearchResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import rx.Observable;

/**
 * Created by Age on 4/20/2017.
 */

public class SearchRemoteDataSource extends BaseRemoteDataSource
        implements SearchDataSource.RemoteDataSource {
    public SearchRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<SearchResponse> search(int domainId, String keyWord) {
        return mFOrderApi.search(domainId, keyWord);
    }
}
