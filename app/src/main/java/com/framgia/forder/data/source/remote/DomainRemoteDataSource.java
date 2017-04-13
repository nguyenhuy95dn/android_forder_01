package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.DomainDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 4/12/2017.
 */

public class DomainRemoteDataSource implements DomainDataSource.RemoteDataSource {
    private FOrderApi mFOrderApi;

    public DomainRemoteDataSource(FOrderApi FOrderApi) {
        mFOrderApi = FOrderApi;
    }
}
