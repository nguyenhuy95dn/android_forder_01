package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.User;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 4/12/2017.
 */

public class DomainRepository {

    private static final String TAG = "DomainRepository";

    private DomainDataSource.RemoteDataSource mDomainRemoteDataSource;
    private DomainDataSource.LocalDataSource mDomainLocalDataSource;

    public DomainRepository(DomainDataSource.RemoteDataSource remoteDataSource,
            DomainDataSource.LocalDataSource localDataSource) {
        mDomainRemoteDataSource = remoteDataSource;
        mDomainLocalDataSource = localDataSource;
    }

    public Observable<List<Domain>> getListDomain() {
        User user = mDomainLocalDataSource.getUser();
        int userId = user.getId();
        String userEmail = user.getEmail();
        String userToken = user.getToken();
        return mDomainRemoteDataSource.getListDomain(userId, userEmail, userToken);
    }

    public void saveDomainId(int id) {
        mDomainLocalDataSource.saveDomainId(id);
    }

    public int getDomainId() {
        return mDomainLocalDataSource.getDomainId();
    }
}
