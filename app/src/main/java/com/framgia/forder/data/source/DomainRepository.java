package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.model.User;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 4/12/2017.
 */

public class DomainRepository {

    private final DomainDataSource.RemoteDataSource mRemoteDataSource;
    private final DomainDataSource.LocalDataSource mLocalDataSource;

    public DomainRepository(DomainDataSource.RemoteDataSource remoteDataSource,
            DomainDataSource.LocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public Observable<List<Domain>> getListDomain() {
        User user = mLocalDataSource.getUser();
        int userId = user.getId();
        String userEmail = user.getEmail();
        String userToken = user.getToken();
        return mRemoteDataSource.getListDomain(userId, userEmail, userToken);
    }

    public void saveCurrentDomain(Domain domain) {
        mLocalDataSource.saveCurrentDomain(domain);
    }

    public Domain getCurrentDomain() {
        return mLocalDataSource.getCurrentDomain();
    }

    public User getUser() {
        return mLocalDataSource.getUser();
    }

    public Observable<List<DomainManagement>> getListDomainManagement() {
        return mRemoteDataSource.getListDomainManagement();
    }
}
