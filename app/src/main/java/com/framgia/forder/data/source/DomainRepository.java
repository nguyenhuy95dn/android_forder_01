package com.framgia.forder.data.source;

import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;

/**
 * Created by Age on 4/12/2017.
 */

public class DomainRepository {
    private DomainRemoteDataSource mDomainRemoteDataSource;
    private DomainLocalDataSource mDomainLocalDataSource;

    public DomainRepository(DomainRemoteDataSource remoteDataSource,
            DomainLocalDataSource localDataSource) {
        mDomainRemoteDataSource = remoteDataSource;
        mDomainLocalDataSource = localDataSource;
    }
}
